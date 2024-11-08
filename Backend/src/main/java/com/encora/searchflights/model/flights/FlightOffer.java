package com.encora.searchflights.model.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FlightOffer {

    private String id; // Flight offer ID

    private List<Itinerary> itineraries; // Contains the flight itinerary details

    @JsonProperty("price")
    private PriceBreakdown priceBreakdown; // Total price in selected currency

    @JsonProperty("travelerPricings")
    private List<TravelerPricing> travelerPricings; // Traveler-specific pricing details
}
