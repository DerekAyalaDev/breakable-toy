package com.encora.searchflights.model.flights;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperatingFlight {
    @JsonProperty("carrierCode")
    private String carrierCode; // Code of the operating airline
}
