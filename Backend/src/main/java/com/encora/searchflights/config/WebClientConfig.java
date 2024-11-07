package com.encora.searchflights.config;

import com.encora.searchflights.model.response.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

    @Value("${amadeus.api.base-url}")
    private String amadeusBaseUrl;

    @Value("${amadeus.api.token-url}")
    private String amadeusTokenUrl;

    @Value("${amadeus.api.key}")
    private String apiKey;

    @Value("${amadeus.api.secret}")
    private String apiSecret;

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl(amadeusBaseUrl)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    public Mono<String> getAccessToken() {
        return WebClient.builder()
                .baseUrl(amadeusTokenUrl)
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("grant_type", "client_credentials")
                        .queryParam("client_id", apiKey)
                        .queryParam("client_secret", apiSecret)
                        .build())
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .map(TokenResponse::getAccessToken);
    }
}
