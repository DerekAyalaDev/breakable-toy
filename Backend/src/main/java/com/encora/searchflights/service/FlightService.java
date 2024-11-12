package com.encora.searchflights.service;

import com.encora.searchflights.model.dto.FlightSearchRequestDTO;
import com.encora.searchflights.model.flights.FlightOffer;

import java.util.List;

public interface FlightService {
    List<FlightOffer> searchFlights(FlightSearchRequestDTO request);
}
