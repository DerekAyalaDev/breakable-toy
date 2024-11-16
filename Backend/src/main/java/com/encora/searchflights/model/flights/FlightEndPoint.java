package com.encora.searchflights.model.flights;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightEndPoint {
    private String iataCode;
    private String terminal;
    private String at; // ISO date-time format
}
