package com.encora.searchflights.service;

import com.encora.searchflights.TestDataHelper;
import com.encora.searchflights.config.WebClientConfig;
import com.encora.searchflights.model.dto.FlightOfferResponseDTO;
import com.encora.searchflights.model.dto.FlightSearchRequestDTO;
import com.encora.searchflights.model.flights.FlightOffer;
import com.encora.searchflights.service.impl.FlightServiceImpl;
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
import java.time.LocalDate;
import java.util.List;

import static com.encora.searchflights.TestDataHelper.getFlightSearchRequestDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FlightServiceTest {
    private MockWebServer mockWebServer;
    private FlightService flightService;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        WebClient webClient = WebClient.builder()
                .baseUrl(mockWebServer.url("/").toString())
                .build();

        WebClientConfig webClientConfig = Mockito.mock(WebClientConfig.class);
        when(webClientConfig.getAccessToken()).thenReturn(Mono.just("fake-token"));

        flightService = new FlightServiceImpl(webClient, webClientConfig);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void testSearchFlights() {
        mockWebServer.enqueue(new MockResponse()
                .setBody(TestDataHelper.getFlightSearchJsonResponse())
                .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE));

        // Create a request DTO with the required parameters
        FlightSearchRequestDTO requestDTO = getFlightSearchRequestDTO(false, false, null);

        // Call the searchFlights method
        Mono<FlightOfferResponseDTO> responseMono = flightService.searchFlights(requestDTO);

        StepVerifier.create(responseMono)
                .assertNext(response -> {
                    assertEquals(1, response.getTotalPages());
                    assertEquals(3, response.getOffers().size());

                    // Verify details of each offer
                    assertEquals("1", response.getOffers().get(0).getId());
                    assertEquals("PT6H30M", response.getOffers().get(0).getItineraries().get(0).getDuration());
                    assertEquals("300.00", response.getOffers().get(0).getPrice().getGrandTotal());

                    assertEquals("2", response.getOffers().get(1).getId());
                    assertEquals("PT7H30M", response.getOffers().get(1).getItineraries().get(0).getDuration());
                    assertEquals("300.00", response.getOffers().get(1).getPrice().getGrandTotal());

                    assertEquals("3", response.getOffers().get(2).getId());
                    assertEquals("PT5H45M", response.getOffers().get(2).getItineraries().get(0).getDuration());
                    assertEquals("250.00", response.getOffers().get(2).getPrice().getGrandTotal());
                })
                .verifyComplete();
    }

    @Test
    void testSearchFlightsSortedByDuration() {
        mockWebServer.enqueue(new MockResponse()
                .setBody(TestDataHelper.getFlightSearchJsonResponse())
                .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE));

        // Create a request DTO with sortByDuration set to true
        FlightSearchRequestDTO requestDTO = getFlightSearchRequestDTO(false, true, null);

        // Call the searchFlights method
        Mono<FlightOfferResponseDTO> responseMono = flightService.searchFlights(requestDTO);

        StepVerifier.create(responseMono)
                .assertNext(response -> {
                    assertEquals(1, response.getTotalPages());
                    assertEquals(3, response.getOffers().size());

                    // Verify that the offers are sorted by duration in ascending order
                    assertEquals("3", response.getOffers().get(0).getId());
                    assertEquals("1", response.getOffers().get(1).getId());
                    assertEquals("2", response.getOffers().get(2).getId());

                    // Additional checks on duration values to confirm sorting
                    assertEquals("PT5H45M", response.getOffers().get(0).getItineraries().get(0).getDuration());
                    assertEquals("PT6H30M", response.getOffers().get(1).getItineraries().get(0).getDuration());
                    assertEquals("PT7H30M", response.getOffers().get(2).getItineraries().get(0).getDuration());
                })
                .verifyComplete();
    }

    @Test
    void testSearchFlightsSortedByPrice() {
        mockWebServer.enqueue(new MockResponse()
                .setBody(TestDataHelper.getFlightSearchJsonResponse())
                .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE));

        // Create a request DTO with sortByPrice set to true
        FlightSearchRequestDTO requestDTO = getFlightSearchRequestDTO(true, false, null);

        // Call the searchFlights method
        Mono<FlightOfferResponseDTO> responseMono = flightService.searchFlights(requestDTO);

        StepVerifier.create(responseMono)
                .assertNext(response -> {
                    assertEquals(1, response.getTotalPages());
                    assertEquals(3, response.getOffers().size());

                    // Verify that the offers are sorted by price in ascending order
                    assertEquals("3", response.getOffers().get(0).getId());
                    assertEquals("1", response.getOffers().get(1).getId());
                    assertEquals("2", response.getOffers().get(2).getId());

                    // Additional checks on price values to confirm sorting
                    assertEquals("250.00", response.getOffers().get(0).getPrice().getGrandTotal());
                    assertEquals("300.00", response.getOffers().get(1).getPrice().getGrandTotal());
                    assertEquals("300.00", response.getOffers().get(2).getPrice().getGrandTotal());
                })
                .verifyComplete();
    }

    @Test
    void testSearchFlightsInvalidDateOrder() {
        // Create a request DTO with a return date before the departure date
        FlightSearchRequestDTO requestDTO = getFlightSearchRequestDTO(false, false, LocalDate.of(2024,11,1));

        // Use StepVerifier to verify that an error is returned
        StepVerifier.create(flightService.searchFlights(requestDTO))
                .expectErrorMatches(throwable -> throwable instanceof IllegalArgumentException &&
                        throwable.getMessage().equals("Return date cannot be earlier than departure date."))
                .verify();
    }

    @Test
    void testSearchFlightsSortedByPriceAndDuration() {
        mockWebServer.enqueue(new MockResponse()
                .setBody(TestDataHelper.getFlightSearchJsonResponse())
                .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE));

        FlightSearchRequestDTO request = getFlightSearchRequestDTO(true, true, null);

        Mono<FlightOfferResponseDTO> responseMono = flightService.searchFlights(request);

        StepVerifier.create(responseMono)
                .assertNext(response -> {
                    List<FlightOffer> offers = response.getOffers();
                    assertEquals(3, offers.size());

                    // Validate the order of flights based on price and duration
                    assertEquals("3", offers.get(0).getId()); // Cheapest price
                    assertEquals("1", offers.get(1).getId()); // Same price, shorter duration
                    assertEquals("2", offers.get(2).getId()); // Same price, longer duration
                })
                .verifyComplete();
    }
}