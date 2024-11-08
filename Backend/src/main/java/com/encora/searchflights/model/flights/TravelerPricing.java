package com.encora.searchflights.model.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TravelerPricing {

    private String travelerType; // E.g., "ADULT"

    @JsonProperty("price")
    private PriceDetail priceDetail; // Price details for this traveler

    @JsonProperty("fareDetailsBySegment")
    private List<FareDetail> fareDetailsBySegment; // Fare details for each segment
}
