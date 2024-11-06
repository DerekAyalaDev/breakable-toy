package com.encora.searchflights.dto;

import com.encora.searchflights.model.AirlineInfo;
import com.encora.searchflights.model.AirportInfo;
import com.encora.searchflights.model.StopInfo;
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

    private AirportInfo departureAirport;
    private AirportInfo arrivalAirport;

    private AirlineInfo mainAirline;
    private AirlineInfo operatingAirline;

    private Duration totalDuration;
    private List<StopInfo> stops;

    private double totalPrice;
    private double pricePerTraveler;
    private String currency;
}
