package com.encora.searchflights.model.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FlightOffer {

    private String id;

    private boolean oneWay;

    private List<Itinerary> itineraries;

    private Price price;

    private PricingOptions pricingOptions;

    private List<TravelerPricing> travelerPricings;
}
