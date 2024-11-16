package com.encora.searchflights.integration;

import com.encora.searchflights.service.FlightService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class FlightControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private FlightService flightService;

    @Test
    void testSearchFlights_MissingOneParameter_ShowsRequiredParameters() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/flights/search")
                        .queryParam("arrivalAirportCode", "LAX")
                        .queryParam("departureDate", "2024-12-01")
                        .queryParam("numberOfAdults", "1")
                        .queryParam("currency", "USD")
                        .build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.requiredParameters.departureAirportCode").isEqualTo("e.g., JFK")
                .jsonPath("$.requiredParameters.arrivalAirportCode").isEqualTo("e.g., LAX")
                .jsonPath("$.requiredParameters.departureDate").isEqualTo("e.g., 2024-12-01 (ISO format)")
                .jsonPath("$.requiredParameters.returnDate").isEqualTo("Optional, e.g., 2024-12-10 (ISO format)")
                .jsonPath("$.requiredParameters.numberOfAdults").isEqualTo("e.g., 1")
                .jsonPath("$.requiredParameters.currency").isEqualTo("e.g., USD")
                .jsonPath("$.message").isEqualTo("Missing required parameters. Ensure the following parameters are included in the request:");
    }

    @Test
    void testSearchFlights_DepartureAirportCodeBlank() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/flights/search")
                        .queryParam("departureAirportCode", "") // Blank value
                        .queryParam("arrivalAirportCode", "LAX")
                        .queryParam("departureDate", "2024-12-01")
                        .queryParam("numberOfAdults", "1")
                        .queryParam("currency", "USD")
                        .build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.violations.departureAirportCode").isEqualTo("Departure airport code is required")
                .jsonPath("$.message").isEqualTo("Validation failed for the provided parameters.");
    }

    @Test
    void testSearchFlights_ArrivalAirportCodeBlank() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/flights/search")
                        .queryParam("departureAirportCode", "JFK")
                        .queryParam("arrivalAirportCode", "") // Blank value
                        .queryParam("departureDate", "2024-12-01")
                        .queryParam("numberOfAdults", "1")
                        .queryParam("currency", "USD")
                        .build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.violations.arrivalAirportCode").isEqualTo("Arrival airport code is required")
                .jsonPath("$.message").isEqualTo("Validation failed for the provided parameters.");
    }

    @Test
    void testSearchFlights_InvalidCurrency() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/flights/search")
                        .queryParam("departureAirportCode", "JFK")
                        .queryParam("arrivalAirportCode", "LAX")
                        .queryParam("departureDate", "2024-12-01")
                        .queryParam("numberOfAdults", "1")
                        .queryParam("currency", "INVALID") // Valor inválido
                        .build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.violations.currency").isEqualTo("Invalid value for parameter 'currency'. Allowed values: USD, MXN, EUR")
                .jsonPath("$.message").isEqualTo("Validation failed for the provided parameters.");
    }

    @Test
    void testSearchFlights_InvalidDateFormat() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/flights/search")
                        .queryParam("departureAirportCode", "JFK")
                        .queryParam("arrivalAirportCode", "LAX")
                        .queryParam("departureDate", "12-01-2024") // Formato inválido
                        .queryParam("numberOfAdults", "1")
                        .queryParam("currency", "USD")
                        .build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.violations.departureDate").isEqualTo("Invalid value for parameter 'departureDate'. Expected format: YYYY-MM-DD")
                .jsonPath("$.message").isEqualTo("Validation failed for the provided parameters.");
    }

    @Test
    void testSearchFlights_DepartureDateInPast() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/flights/search")
                        .queryParam("departureAirportCode", "JFK")
                        .queryParam("arrivalAirportCode", "LAX")
                        .queryParam("departureDate", "2023-01-01") // Fecha en el pasado
                        .queryParam("numberOfAdults", "1")
                        .queryParam("currency", "USD")
                        .build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.violations.departureDate").isEqualTo("Departure date cannot be in the past")
                .jsonPath("$.message").isEqualTo("Validation failed for the provided parameters.");
    }

    @Test
    void testSearchFlights_ArrivalDateInPast() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/flights/search")
                        .queryParam("departureAirportCode", "JFK")
                        .queryParam("arrivalAirportCode", "LAX")
                        .queryParam("departureDate", "2024-12-01")
                        .queryParam("returnDate", "2023-11-01") // Fecha en el pasado
                        .queryParam("numberOfAdults", "1")
                        .queryParam("currency", "USD")
                        .build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.violations.returnDate").isEqualTo("Return date cannot be in the past")
                .jsonPath("$.message").isEqualTo("Validation failed for the provided parameters.");
    }

    @Test
    void testSearchFlights_NumberOfAdultsLessThanOne() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/flights/search")
                        .queryParam("departureAirportCode", "JFK")
                        .queryParam("arrivalAirportCode", "LAX")
                        .queryParam("departureDate", "2024-12-01")
                        .queryParam("numberOfAdults", "0") // Menor a 1
                        .queryParam("currency", "USD")
                        .build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.violations.numberOfAdults").isEqualTo("At least one adult is required")
                .jsonPath("$.message").isEqualTo("Validation failed for the provided parameters.");
    }

    @Test
    void testSearchFlights_MultipleValidationErrors() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/flights/search")
                        .queryParam("departureAirportCode", "") // Empty
                        .queryParam("arrivalAirportCode", "") // Empty
                        .queryParam("departureDate", "2023-12-01") // Past date
                        .queryParam("returnDate", "2023-12-01") // Past date
                        .queryParam("numberOfAdults", "0") // Invalid value
                        .queryParam("currency", "USD") // Valid currency
                        .build())
                .exchange()
                .expectStatus().isBadRequest() // Expect 400 Bad Request
                .expectBody()
                // Verify each validation error in the 'violations' field
                .jsonPath("$.violations.departureAirportCode").isEqualTo("Departure airport code is required")
                .jsonPath("$.violations.arrivalAirportCode").isEqualTo("Arrival airport code is required")
                .jsonPath("$.violations.departureDate").isEqualTo("Departure date cannot be in the past")
                .jsonPath("$.violations.returnDate").isEqualTo("Return date cannot be in the past")
                .jsonPath("$.violations.numberOfAdults").isEqualTo("At least one adult is required")
                // Verify the general validation message
                .jsonPath("$.message").isEqualTo("Validation failed for the provided parameters.");
    }
}
