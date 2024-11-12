package com.encora.searchflights.controller;

import com.encora.searchflights.model.airport.AirportInfo;
import com.encora.searchflights.service.AirportService;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Mono<ResponseEntity<List<AirportInfo>>> searchAirports(@RequestParam @NotBlank(message = "The 'keyword' parameter must not be blank") String keyword) {
        return airportService.searchAirportsByName(keyword).map(airports -> ResponseEntity.ok(airports));
    }

    @GetMapping("/get")
    public ResponseEntity<AirportInfo> getAirportByIataCode(@RequestParam @NotBlank(message = "The 'iataCode' parameter must not be blank") String iataCode) {
        AirportInfo airportInfo = airportService.getAirportByIataCode(iataCode);
        if (airportInfo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(airportInfo);
    }
}
