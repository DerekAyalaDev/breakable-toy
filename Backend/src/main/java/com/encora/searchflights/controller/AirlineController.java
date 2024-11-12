package com.encora.searchflights.controller;

import com.encora.searchflights.model.airline.AirlineInfo;
import com.encora.searchflights.service.AirlineService;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/airlines")
public class AirlineController {
    private final AirlineService airlineService;

    @GetMapping("/get")
    public ResponseEntity<AirlineInfo> getAirlineInfo(@RequestParam @NotBlank(message = "Airline code must not be blank") String airlineCode) {
        AirlineInfo airlineInfo = airlineService.getAirlineInfo(airlineCode);
        if (airlineInfo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(airlineInfo);
    }
}
