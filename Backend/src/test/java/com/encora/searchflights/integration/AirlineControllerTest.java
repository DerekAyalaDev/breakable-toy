package com.encora.searchflights.integration;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.encora.searchflights.model.airline.AirlineInfo;
import com.encora.searchflights.service.AirlineService;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
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
                .expectStatus().isBadRequest() // Expect 400 Bad Request
                .expectBody()
                // Verify the violations field contains the specific error for airlineCode
                .jsonPath("$.violations.airlineCode").isEqualTo("Airline code must not be blank")
                // Verify the general validation message
                .jsonPath("$.message").isEqualTo("Validation failed for the provided parameters.");
    }

    @Test
    void testGetAirlineInfo_MissingAirlineCode() {
        // Perform the GET request without the airlineCode parameter
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/airlines/get").build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.requiredParameters").exists()
                .jsonPath("$.requiredParameters.airlineCode").isEqualTo("e.g., AA (IATA code)")
                .jsonPath("$.message")
                .isEqualTo("Missing required parameters. Ensure the following parameters are included in the request:");
    }
}
