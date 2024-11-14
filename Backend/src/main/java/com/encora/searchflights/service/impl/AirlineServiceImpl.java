package com.encora.searchflights.service.impl;

import com.encora.searchflights.config.WebClientConfig;
import com.encora.searchflights.model.airline.AirlineInfo;
import com.encora.searchflights.model.airline.AirlineResponse;
import com.encora.searchflights.service.AirlineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpStatus;

@Service
@AllArgsConstructor
public class AirlineServiceImpl implements AirlineService {
    private final WebClient webClient;
    private final WebClientConfig webClientConfig;

    @Override
    public Mono<AirlineInfo> getAirlineInfo(String airlineCode) {
        String token = webClientConfig.getAccessToken().block(); // Asumiendo que WebClientConfig tiene un método para obtener el token.

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/reference-data/airlines")
                        .queryParam("airlineCodes", airlineCode)
                        .build())
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .onStatus(status -> status.equals(HttpStatus.NOT_FOUND) || status.is4xxClientError(),
                        clientResponse -> Mono.empty()) // Manejar 404 y otros errores del cliente sin lanzar una excepción
                .bodyToMono(AirlineResponse.class)
                .flatMap(response -> {
                    if (response.getData() != null && !response.getData().isEmpty()) {
                        return Mono.just(response.getData().get(0)); // Retorna el primer item si está disponible
                    }
                    return Mono.empty();
                });
    }
}
