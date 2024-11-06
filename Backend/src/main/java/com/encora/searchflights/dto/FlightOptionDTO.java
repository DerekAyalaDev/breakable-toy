package com.encora.searchflights.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class FlightOptionDTO {

    private LocalDateTime initialDepartureDateTime;
    private LocalDateTime finalArrivalDateTime;

    private AirportInfoDTO departureAirport;
    private AirportInfoDTO arrivalAirport;

    private AirlineInfoDTO mainAirline;
    private AirlineInfoDTO operatingAirline;

    private Duration totalDuration;
    private List<StopInfoDTO> stops;

    private double totalPrice;
    private double pricePerTraveler;
    private String currency;
}
