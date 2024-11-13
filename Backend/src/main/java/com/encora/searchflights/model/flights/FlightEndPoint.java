package com.encora.searchflights.model.flights;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FlightEndPoint {

    private String iataCode;

    private String terminal;

    private String at; // ISO date-time format
}
