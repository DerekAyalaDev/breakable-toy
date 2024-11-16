package com.encora.searchflights.model.flights;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

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
