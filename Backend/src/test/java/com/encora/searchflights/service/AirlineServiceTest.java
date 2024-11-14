package com.encora.searchflights.service;

import com.encora.searchflights.config.WebClientConfig;
import com.encora.searchflights.model.airline.AirlineInfo;
import com.encora.searchflights.service.impl.AirlineServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AirlineServiceTest {
    private MockWebServer mockWebServer;
    private AirlineService airlineService;
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

        airlineService = new AirlineServiceImpl(webClient, webClientConfig);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void testGetAirlineInfo_Found() {
        String airlineJson = "{ \"data\": [{ \"iataCode\": \"AA\", \"businessName\": \"American Airlines\" }] }";
        mockWebServer.enqueue(new MockResponse()
                .setBody(airlineJson)
                .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE));

        Mono<AirlineInfo> airlineInfoMono = airlineService.getAirlineInfo("AA");

        StepVerifier.create(airlineInfoMono)
                .assertNext(airlineInfo -> {
                    assertEquals("American Airlines", airlineInfo.getName());
                    assertEquals("AA", airlineInfo.getCode());
                })
                .verifyComplete();
    }

    @Test
    void testGetAirlineInfo_NotFound() {
        String emptyResponseJson = "{ \"data\": [] }";
        mockWebServer.enqueue(new MockResponse()
                .setBody(emptyResponseJson)
                .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE));

        Mono<AirlineInfo> airlineInfoMono = airlineService.getAirlineInfo("XX");

        StepVerifier.create(airlineInfoMono)
                .expectComplete()
                .verify();
    }
}

