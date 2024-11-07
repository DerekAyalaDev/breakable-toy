package com.encora.searchflights.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirlineInfo {
    @JsonProperty("commonName")
    private String name;

    @JsonProperty("iataCode")
    private String code;
}
