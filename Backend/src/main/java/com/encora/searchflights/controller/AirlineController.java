package com.encora.searchflights.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.encora.searchflights.model.airline.AirlineInfo;
import com.encora.searchflights.service.AirlineService;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@AllArgsConstructor
@RequestMapping("api/airlines")
@Validated
public class AirlineController {
    private final AirlineService airlineService;

    @GetMapping("/get")
    public Mono<ResponseEntity<AirlineInfo>> getAirlineInfo(
            @RequestParam @NotBlank(message = "Airline code must not be blank") String airlineCode) {
        return airlineService.getAirlineInfo(airlineCode)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
