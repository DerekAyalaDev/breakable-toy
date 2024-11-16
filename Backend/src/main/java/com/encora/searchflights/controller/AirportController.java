package com.encora.searchflights.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.encora.searchflights.model.airport.AirportInfo;
import com.encora.searchflights.service.AirportService;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("api/airports")
@Validated
public class AirportController {
    private final AirportService airportService;

    @GetMapping("/search")
    public Mono<ResponseEntity<List<AirportInfo>>> searchAirports(
            @RequestParam @NotBlank(message = "The 'keyword' parameter must not be blank") String keyword) {
        return airportService.searchAirportsByName(keyword).map(airports -> ResponseEntity.ok(airports));
    }

    @GetMapping("/get")
    public Mono<ResponseEntity<AirportInfo>> getAirportByIataCode(
            @RequestParam @NotBlank(message = "The 'iataCode' parameter must not be blank") String iataCode) {
        return airportService.getAirportByIataCode(iataCode)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
