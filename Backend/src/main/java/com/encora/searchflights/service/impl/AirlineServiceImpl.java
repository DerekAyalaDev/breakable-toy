package com.encora.searchflights.service.impl;

import com.encora.searchflights.model.AirlineInfo;
import com.encora.searchflights.service.AirlineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class AirlineServiceImpl implements AirlineService {
    private final WebClient webClient;

    @Override
    public AirlineInfo getAirlineInfo(String airlineCode) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/reference-data/airlines")
                        .queryParam("airlineCodes", airlineCode)
                        .build())
                .retrieve()
                .bodyToMono(AirlineInfo.class)
                .block();
    }
}
