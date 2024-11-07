package com.encora.searchflights.service.impl;

import com.encora.searchflights.model.AirportInfo;
import com.encora.searchflights.service.AirportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@AllArgsConstructor
public class AirportServiceImpl implements AirportService {
    private final WebClient webClient;

    @Override
    public List<AirportInfo> searchAirportsByName(String keyword) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/reference-data/locations")
                        .queryParam("subType","AIRPORT")
                        .queryParam("keyword", keyword)
                        .build())
                .retrieve()
                .bodyToFlux(AirportInfo.class)
                .collectList()
                .block();
    }
}
