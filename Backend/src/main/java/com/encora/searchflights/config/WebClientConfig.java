package com.encora.searchflights.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.encora.searchflights.model.response.TokenResponse;

import io.github.cdimascio.dotenv.Dotenv;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

    private final Dotenv dotenv = Dotenv.load();

    @Bean
    public String amadeusBaseUrl() {
        return dotenv.get("AMADEUS_BASE_URL");
    }

    @Bean
    public String apiKey() {
        return dotenv.get("AMADEUS_API_KEY");
    }

    @Bean
    public String apiSecret() {
        return dotenv.get("AMADEUS_API_SECRET");
    }

    @Bean
    public String amadeusTokenUrl() {
        return dotenv.get("AMADEUS_TOKEN_URL");
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl(amadeusBaseUrl())
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    public Mono<String> getAccessToken() {
        return WebClient.builder()
                .baseUrl(amadeusTokenUrl())
                .build()
                .post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData("grant_type", "client_credentials")
                        .with("client_id", apiKey())
                        .with("client_secret", apiSecret()))
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .map(TokenResponse::getAccessToken);
    }
}
