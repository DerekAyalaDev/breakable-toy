package com.encora.searchflights.model.dto;

import com.encora.searchflights.enums.Currency;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FlightSearchRequestDTO {

    @NotBlank(message = "Departure airport code is required")
    private String departureAirportCode;

    @NotBlank(message = "Arrival airport code is required")
    private String arrivalAirportCode;

    @NotNull(message = "Departure date is required")
    @FutureOrPresent(message = "Departure date cannot be in the past")
    private LocalDate departureDate;

    @FutureOrPresent(message = "Return date cannot be in the past")
    private LocalDate returnDate;

    @Min(value = 1, message = "At least one adult is required")
    private int numberOfAdults;

    @NotBlank(message = "Currency is required")
    private Currency currency;

    private boolean nonStop;

    private int pageNumber=1;
    private String sortByPrice = "";
    private String sortByDuration = "";

    private boolean showAll = false;
}
