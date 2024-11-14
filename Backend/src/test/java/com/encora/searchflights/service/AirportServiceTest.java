package com.encora.searchflights.service;

import com.encora.searchflights.config.WebClientConfig;
import com.encora.searchflights.model.airport.AirportInfo;
import com.encora.searchflights.service.impl.AirportServiceImpl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class AirportServiceTest {
    private MockWebServer mockWebServer;
    private AirportService airportService;
    private WebClientConfig webClientConfig;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        WebClient webClient = WebClient.builder()
                .baseUrl(mockWebServer.url("/").toString())
                .build();

        webClientConfig = Mockito.mock(WebClientConfig.class);
        when(webClientConfig.getAccessToken()).thenReturn(Mono.just("fake-token"));

        airportService = new AirportServiceImpl(webClient, webClientConfig);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void testSearchAirportsByName_FoundPartialMatches() {
        String responseJson = "{ \"data\": [" +
                "{ \"name\": \"MANCHESTER AIRPORT\", \"iataCode\": \"MAN\", \"address\": { \"cityName\": \"MANCHESTER\", \"countryName\": \"UNITED KINGDOM\" } }," +
                "{ \"name\": \"MANCHESTER BOSTON RGNL\", \"iataCode\": \"MHT\", \"address\": { \"cityName\": \"MANCHESTER\", \"countryName\": \"UNITED STATES OF AMERICA\" } }" +
                "] }";

        mockWebServer.enqueue(new MockResponse()
                .setBody(responseJson)
                .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE));

        Mono<List<AirportInfo>> result = airportService.searchAirportsByName("MAN");

        StepVerifier.create(result)
                .assertNext(airports -> {
                    assertEquals(2, airports.size());

                    // Verify first airport details
                    AirportInfo airport1 = airports.get(0);
                    assertEquals("MANCHESTER AIRPORT", airport1.getName());
                    assertEquals("MAN", airport1.getIataCode());
                    assertEquals("MANCHESTER", airport1.getAddress().getCity());
                    assertEquals("UNITED KINGDOM", airport1.getAddress().getCountry());

                    // Verify second airport details
                    AirportInfo airport2 = airports.get(1);
                    assertEquals("MANCHESTER BOSTON RGNL", airport2.getName());
                    assertEquals("MHT", airport2.getIataCode());
                    assertEquals("MANCHESTER", airport2.getAddress().getCity());
                    assertEquals("UNITED STATES OF AMERICA", airport2.getAddress().getCountry());
                })
                .verifyComplete();
    }

    @Test
    void testSearchAirportsByName_NotFound() {
        String responseJson = "{ \"data\": [] }";
        mockWebServer.enqueue(new MockResponse()
                .setBody(responseJson)
                .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE));

        Mono<List<AirportInfo>> result = airportService.searchAirportsByName("UNKNOWN");

        StepVerifier.create(result)
                .assertNext(airports -> assertTrue(airports.isEmpty()))
                .verifyComplete();
    }

    @Test
    void testGetAirportByIataCode_Found() {
        String responseJson = "{ \"data\": [{ \"name\": \"JFK Airport\", \"iataCode\": \"JFK\", \"cityName\": \"New York\" }] }";
        mockWebServer.enqueue(new MockResponse()
                .setBody(responseJson)
                .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE));

        Mono<AirportInfo> result = airportService.getAirportByIataCode("JFK");

        StepVerifier.create(result)
                .assertNext(airport -> {
                    assertEquals("JFK Airport", airport.getName());
                    assertEquals("JFK", airport.getIataCode());
                })
                .verifyComplete();
    }

    @Test
    void testGetAirportByIataCode_NotFound() {
        String responseJson = "{ \"data\": [] }";
        mockWebServer.enqueue(new MockResponse()
                .setBody(responseJson)
                .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE));

        Mono<AirportInfo> result = airportService.getAirportByIataCode("UNKNOWN");

        StepVerifier.create(result)
                .expectNextCount(0)
                .verifyComplete();
    }
}
