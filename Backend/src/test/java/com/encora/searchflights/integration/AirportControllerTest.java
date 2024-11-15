package com.encora.searchflights.integration;

import com.encora.searchflights.controller.AirportController;
import com.encora.searchflights.model.airport.AirportInfo;
import com.encora.searchflights.service.AirportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

import static com.encora.searchflights.TestDataHelper.createAirportInfo;
import static org.mockito.Mockito.when;

@WebFluxTest(AirportController.class)
@ExtendWith(SpringExtension.class)
public class AirportControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private AirportService airportService;

    @Test
    void TestSearchAirports_Success() {
        List<AirportInfo> mockAirportList = List.of(
                createAirportInfo("Manchester Airport", "MAN", "Manchester", "United Kingdom"),
                createAirportInfo("Manchester Boston Regional", "MHT", "Manchester", "United States")
        );

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
                .uri(uriBuilder -> uriBuilder.path("/api/airports/search").queryParam("keyword", "UnknownPlace").build())
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
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.keyword").isEqualTo("The 'keyword' parameter must not be blank");
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
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.iataCode").isEqualTo("The 'iataCode' parameter must not be blank");
    }
}
