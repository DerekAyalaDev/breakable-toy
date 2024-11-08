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

    @JsonProperty("address.cityName")
    private String city; // City where the airport is located, assuming flat mapping doesn't work

    @JsonProperty("address.countryCode")
    private String country; // Country code, assuming flat mapping doesn't work
}

