package com.encora.searchflights.controller;

import com.encora.searchflights.model.airline.AirlineInfo;
import com.encora.searchflights.service.AirlineService;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("api/airlines")
@Validated
public class AirlineController {
    private final AirlineService airlineService;

    @GetMapping("/get")
    public Mono<ResponseEntity<AirlineInfo>> getAirlineInfo(@RequestParam @NotBlank(message = "Airline code must not be blank") String airlineCode) {
        return airlineService.getAirlineInfo(airlineCode)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
