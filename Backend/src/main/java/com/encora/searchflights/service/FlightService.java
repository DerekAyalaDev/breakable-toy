package com.encora.searchflights.service;

import com.encora.searchflights.model.dto.FlightOfferResponseDTO;
import com.encora.searchflights.model.dto.FlightSearchRequestDTO;

import java.util.List;

public interface FlightService {

    /**
     * Searches for flight offers based on the criteria specified in the request.
     * The method fetches offers from the Amadeus API, sorts them by specified criteria,
     * paginates the results, and returns a subset of flight offers and total pages.
     *
     * @param request the search criteria including origin, destination, dates, sorting, and pagination info.
     * @return a response DTO containing a paginated list of flight offers and the total page count.
     */
    FlightOfferResponseDTO searchFlights(FlightSearchRequestDTO request);
}
