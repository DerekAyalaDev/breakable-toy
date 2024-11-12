package com.encora.searchflights.service.impl;

import com.encora.searchflights.model.dto.FlightSearchRequestDTO;
import com.encora.searchflights.model.flights.FlightOffer;
import com.encora.searchflights.service.FlightService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Setter
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final WebClient webClient;

    @Override
    public List<FlightOffer> searchFlights(FlightSearchRequestDTO request) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/shopping/flight-offers")
                        .queryParam("originLocationCode", request.getDepartureAirportCode())
                        .queryParam("destinationLocationCode", request.getArrivalAirportCode())
                        .queryParam("departureDate", request.getDepartureDate().toString())
                        .queryParam("returnDate", request.getReturnDate() != null ? request.getReturnDate().toString() : null)
                        .queryParam("adults", request.getNumberOfAdults())
                        .queryParam("currencyCode", request.getCurrency())
                        .queryParam("nonStop", request.isNonStop())
                        .build())
                .retrieve()
                .bodyToFlux(FlightOffer.class)
                .collectList()
                .block();
    }
}
