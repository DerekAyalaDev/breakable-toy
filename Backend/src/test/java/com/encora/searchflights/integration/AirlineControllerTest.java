package com.encora.searchflights.integration;

import com.encora.searchflights.controller.AirlineController;
import com.encora.searchflights.model.airline.AirlineInfo;
import com.encora.searchflights.service.AirlineService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@WebFluxTest(AirlineController.class)
@ExtendWith(SpringExtension.class)
public class AirlineControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AirlineService airlineService;

    @Test
    void testGetAirlineInfo_Success() {
        // Mock the service response
        AirlineInfo mockAirlineInfo = new AirlineInfo();
        mockAirlineInfo.setName("American Airlines");
        mockAirlineInfo.setCode("AA");

        when(airlineService.getAirlineInfo("AA")).thenReturn(Mono.just(mockAirlineInfo));

        // Perform the GET request and validate the response
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/airlines/get").queryParam("airlineCode", "AA").build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.businessName").isEqualTo("American Airlines")
                .jsonPath("$.iataCode").isEqualTo("AA");
    }

    @Test
    void testGetAirlineInfo_NotFound() {
        // Mock the service response for a non-existent airline
        when(airlineService.getAirlineInfo("XX")).thenReturn(Mono.empty());

        // Perform the GET request and validate the response
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/airlines/get").queryParam("airlineCode", "XX").build())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testGetAirlineInfo_BlankAirlineCode() {
        // Perform the GET request with a blank airlineCode
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/airlines/get").queryParam("airlineCode", "").build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.airlineCode").isEqualTo("Airline code must not be blank"); // Match the exact response
    }

}
