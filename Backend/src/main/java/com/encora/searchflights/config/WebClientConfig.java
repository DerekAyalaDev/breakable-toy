package com.encora.searchflights.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${amadeus.api.base-url}")
    private String amadeusBaseUrl;

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl(amadeusBaseUrl).defaultHeader("Content-Type", "application/json").build();
    }
}
