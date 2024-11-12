package com.encora.searchflights.service.impl;

import com.encora.searchflights.config.WebClientConfig;
import com.encora.searchflights.model.airport.AirportInfo;
import com.encora.searchflights.service.AirportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class AirportServiceImpl implements AirportService {
    private final WebClient webClient;
    private final WebClientConfig webClientConfig;

    @Override
    public Mono<List<AirportInfo>> searchAirportsByName(String keyword) {
        return webClientConfig.getAccessToken()
                .flatMapMany(token -> webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/v1/reference-data/locations")
                                .queryParam("subType", "AIRPORT")
                                .queryParam("keyword", keyword)
                                .queryParam("page[limit]", 10) // Limit the results for simplicity
                                .build())
                        .header("Authorization", "Bearer " + token) // Set the token in the Authorization header
                        .retrieve()
                        .bodyToFlux(AirportInfo.class))
                .collectList();
    }
}
