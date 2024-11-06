package com.encora.searchflights.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Segment {
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private AirlineInfo airline;
    private AirlineInfo operatingAirline;
    private String flightNumber;
    private String aircraftType;
    private FareDetail fareDetail;
}
