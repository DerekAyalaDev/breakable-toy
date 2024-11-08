package com.encora.searchflights.service;

import com.encora.searchflights.model.airport.AirportInfo;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AirportService {
    Mono<List<AirportInfo>> searchAirportsByName(String keyword);
}
