package com.encora.searchflights.model.airport;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirportResponse {
    @JsonProperty("data")
    private List<AirportInfo> data;
}
