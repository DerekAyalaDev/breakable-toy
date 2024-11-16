package com.encora.searchflights.model.airline;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirlineInfo {
    @JsonProperty("businessName")
    private String name; // Airline name

    @JsonProperty("iataCode")
    private String code; // Airline IATA code
}
