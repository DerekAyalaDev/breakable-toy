package com.encora.searchflights.model.airline;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AirlineResponse {
    @JsonProperty("data")
    private List<AirlineInfo> data;
}
