package com.encora.searchflights.controller;

import com.encora.searchflights.model.airport.AirportInfo;
import com.encora.searchflights.service.AirportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/airports")
public class AirportController {
    private final AirportService airportService;

    @GetMapping("/search")
    public Mono<List<AirportInfo>> searchAirports(@RequestParam String keyword) {
        return airportService.searchAirportsByName(keyword);
    }

    @GetMapping("/get")
    public AirportInfo getAirportByIataCode(@RequestParam String iataCode) {
        return airportService.getAirportByIataCode(iataCode);
    }
}
