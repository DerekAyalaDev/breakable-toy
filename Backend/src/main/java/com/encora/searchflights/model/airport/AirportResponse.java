package com.encora.searchflights.model.airport;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AirportResponse {
    @JsonProperty("data")
    private List<AirportInfo> data;
}
