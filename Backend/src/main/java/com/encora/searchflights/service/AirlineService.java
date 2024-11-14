package com.encora.searchflights.service;

import com.encora.searchflights.model.airline.AirlineInfo;
import reactor.core.publisher.Mono;

public interface AirlineService {
    /**
     * Fetches airline information based on the airline code asynchronously.
     *
     * @param airlineCode the airline code to fetch information for
     * @return a Mono of AirlineInfo if found, otherwise an empty Mono
     */
    Mono<AirlineInfo> getAirlineInfo(String airlineCode);
}
