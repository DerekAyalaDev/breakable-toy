package com.encora.searchflights.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.encora.searchflights.enums.Currency;
import com.encora.searchflights.model.dto.FlightOfferResponseDTO;
import com.encora.searchflights.model.dto.FlightSearchRequestDTO;
import com.encora.searchflights.service.FlightService;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("api/flights")
@AllArgsConstructor
@Validated
public class FlightController {
    private final FlightService flightService;

    @GetMapping("/search")
    public Mono<ResponseEntity<FlightOfferResponseDTO>> searchFlights(
            @RequestParam @NotBlank(message = "Departure airport code is required") String departureAirportCode,
            @RequestParam @NotBlank(message = "Arrival airport code is required") String arrivalAirportCode,
            @RequestParam @NotNull(message = "Departure date is required") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @FutureOrPresent(message = "Departure date cannot be in the past") LocalDate departureDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @FutureOrPresent(message = "Return date cannot be in the past") LocalDate returnDate,
            @RequestParam @Min(value = 1, message = "At least one adult is required") int numberOfAdults,
            @RequestParam @NotNull(message = "Currency is required") Currency currency,
            @RequestParam(defaultValue = "false") boolean nonStop,
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "false") boolean sortByPrice,
            @RequestParam(defaultValue = "false") boolean sortByDuration) {
        // Create FlightSearchRequestDTO with validated parameters
        FlightSearchRequestDTO requestDTO = new FlightSearchRequestDTO(
                departureAirportCode, arrivalAirportCode, departureDate, returnDate,
                numberOfAdults, currency, nonStop, pageNumber, sortByPrice, sortByDuration);

        // Call service to search flights and return response reactively
        return flightService.searchFlights(requestDTO).map(ResponseEntity::ok);
    }
}
