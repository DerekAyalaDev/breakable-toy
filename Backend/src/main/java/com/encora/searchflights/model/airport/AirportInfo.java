package com.encora.searchflights.model.airport;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirportInfo {
    private String name; // Airport name

    @JsonProperty("iataCode")
    private String iataCode; // IATA code

    @JsonProperty("address")
    private Address address; // Address information

    // Inner class for Address
    @Getter
    @Setter
    public static class Address {
        @JsonProperty("cityName")
        private String city;

        @JsonProperty("countryName")
        private String country;
    }
}
