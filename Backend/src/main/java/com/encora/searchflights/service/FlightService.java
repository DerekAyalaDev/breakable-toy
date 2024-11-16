package com.encora.searchflights.service;

import com.encora.searchflights.model.dto.FlightOfferResponseDTO;
import com.encora.searchflights.model.dto.FlightSearchRequestDTO;

import reactor.core.publisher.Mono;

public interface FlightService {
    /**
     * Searches for flight offers based on the provided criteria in the request.
     *
     * @param request the search criteria including origin, destination, dates,
     *                sorting, and pagination.
     * @return a Mono containing a response DTO with paginated flight offers and the
     *         total page count.
     */
    Mono<FlightOfferResponseDTO> searchFlights(FlightSearchRequestDTO request);
}
