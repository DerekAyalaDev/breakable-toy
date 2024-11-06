package com.encora.searchflights.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SegmentDTO {

    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private AirlineInfoDTO airline;
    private AirlineInfoDTO operatingAirline;
    private String flightNumber;
    private String aircraftType;
    private FareDetailDTO fareDetail;
}
