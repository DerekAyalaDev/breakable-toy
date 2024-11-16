package com.encora.searchflights.service;

import java.util.List;

import com.encora.searchflights.model.airport.AirportInfo;

import reactor.core.publisher.Mono;

public interface AirportService {
    Mono<List<AirportInfo>> searchAirportsByName(String keyword);

    Mono<AirportInfo> getAirportByIataCode(String iataCode);
}
