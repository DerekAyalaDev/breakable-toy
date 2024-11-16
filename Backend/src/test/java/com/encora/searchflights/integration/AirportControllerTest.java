package com.encora.searchflights.integration;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.encora.searchflights.TestDataHelper.createAirportInfo;
import com.encora.searchflights.model.airport.AirportInfo;
import com.encora.searchflights.service.AirportService;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class AirportControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private AirportService airportService;

    @Test
    void TestSearchAirports_Success() {
        List<AirportInfo> mockAirportList = List.of(
                createAirportInfo("Manchester Airport", "MAN", "Manchester", "United Kingdom"),
                createAirportInfo("Manchester Boston Regional", "MHT", "Manchester", "United States"));

        when(airportService.searchAirportsByName("Manchester")).thenReturn(Mono.just(mockAirportList));

        // Perform the GET request and validate the response
        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/airports/search").queryParam("keyword", "Manchester").build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(2)
                .jsonPath("$[0].name").isEqualTo("Manchester Airport")
                .jsonPath("$[0].iataCode").isEqualTo("MAN")
                .jsonPath("$[0].address.cityName").isEqualTo("Manchester")
                .jsonPath("$[0].address.countryName").isEqualTo("United Kingdom")
                .jsonPath("$[1].name").isEqualTo("Manchester Boston Regional")
                .jsonPath("$[1].iataCode").isEqualTo("MHT")
                .jsonPath("$[1].address.cityName").isEqualTo("Manchester")
                .jsonPath("$[1].address.countryName").isEqualTo("United States");
    }

    @Test
    void TestSearchAirports_EmptyResult() {
        // Mock the service response to return an empty list
        when(airportService.searchAirportsByName("UnknownPlace")).thenReturn(Mono.just(Collections.emptyList()));

        // Perform the GET request and validate the response
        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/airports/search").queryParam("keyword", "UnknownPlace")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(0); // Ensure the response is an empty array
    }

    @Test
    void TestSearchAirports_KeywordBlank() {
        // Perform the GET request with a blank keyword
        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/airports/search").queryParam("keyword", "").build())
                .exchange()
                .expectStatus().isBadRequest() // Expect 400 Bad Request
                .expectBody()
                // Verify the violations field contains the specific error for keyword
                .jsonPath("$.violations.keyword").isEqualTo("The 'keyword' parameter must not be blank")
                // Verify the general validation message
                .jsonPath("$.message").isEqualTo("Validation failed for the provided parameters.");
    }

    @Test
    void testSearchAirports_MissingKeyword() {
        // Perform the GET request without 'keyword'
        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/airports/search").build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .consumeWith(response -> {
                    String responseBody = new String(response.getResponseBody(), StandardCharsets.UTF_8);
                    System.out.println("Response: " + responseBody); // Inspección adicional
                })
                .jsonPath("$.requiredParameters.keyword").isEqualTo("e.g., Manchester")
                .jsonPath("$.message")
                .isEqualTo("Missing required parameters. Ensure the following parameters are included in the request:");
    }

    @Test
    void TestGetAirportByIataCode_Success() {
        // Mock the service response
        AirportInfo mockAirportInfo = createAirportInfo("Manchester Airport", "MAN", "Manchester", "United Kingdom");

        when(airportService.getAirportByIataCode("MAN")).thenReturn(Mono.just(mockAirportInfo));

        // Perform the GET request and validate the response
        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/airports/get").queryParam("iataCode", "MAN").build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Manchester Airport")
                .jsonPath("$.iataCode").isEqualTo("MAN")
                .jsonPath("$.address.cityName").isEqualTo("Manchester")
                .jsonPath("$.address.countryName").isEqualTo("United Kingdom");
    }

    @Test
    void TestGetAirportByIataCode_NotFound() {
        // Mock the service response for a non-existent airport
        when(airportService.getAirportByIataCode("XXX")).thenReturn(Mono.empty());

        // Perform the GET request and validate the response
        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/airports/get").queryParam("iataCode", "XXX").build())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void TestGetAirportByIataCode_IataCodeBlank() {
        // Perform the GET request with a blank iataCode
        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/airports/get").queryParam("iataCode", "").build())
                .exchange()
                .expectStatus().isBadRequest() // Expect 400 Bad Request
                .expectBody()
                // Verify the violations field contains the specific error for iataCode
                .jsonPath("$.violations.iataCode").isEqualTo("The 'iataCode' parameter must not be blank")
                // Verify the general validation message
                .jsonPath("$.message").isEqualTo("Validation failed for the provided parameters.");
    }

    @Test
    void testGetAirportByIataCode_MissingIataCode() {
        // Perform the GET request without 'iataCode'
        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/airports/get").build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.requiredParameters.iataCode").isEqualTo("e.g., MAN")
                .jsonPath("$.message")
                .isEqualTo("Missing required parameters. Ensure the following parameters are included in the request:");
    }
}
