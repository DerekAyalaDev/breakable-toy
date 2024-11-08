package com.encora.searchflights.model.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FareDetail {

    private String cabin; // Cabin class (e.g., "ECONOMY")

    @JsonProperty("class")
    private String fareClass; // Fare class code (e.g., "E")

    private IncludedCheckedBags includedCheckedBags; // Baggage details
}
