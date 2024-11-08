package com.encora.searchflights.model.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FlightEndPoint {

    private String iataCode; // Airport code

    @JsonProperty("at")
    private LocalDateTime dateTime; // Departure or arrival time
}
