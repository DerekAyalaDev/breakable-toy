package com.encora.searchflights.model.dto;

import java.time.LocalDate;

import com.encora.searchflights.enums.Currency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightSearchRequestDTO {
    private String departureAirportCode;
    private String arrivalAirportCode;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private int numberOfAdults;
    private Currency currency;
    private boolean nonStop;
    private int pageNumber = 1;
    private boolean sortByPrice = false;
    private boolean sortByDuration = false;
}
