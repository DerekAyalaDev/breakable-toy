package com.encora.searchflights.model.airline;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirlineResponse {
    @JsonProperty("data")
    private List<AirlineInfo> data;
}
