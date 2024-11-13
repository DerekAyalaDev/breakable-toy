package com.encora.searchflights.model.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FlightOffer {

    private String type; // "flight-offer"

    private String id;

    private String source;

    private boolean instantTicketingRequired;

    private boolean nonHomogeneous;

    private boolean oneWay;

    private String lastTicketingDate;

    private int numberOfBookableSeats;

    private List<Itinerary> itineraries;

    private Price price;

    private PricingOptions pricingOptions;

    private List<String> validatingAirlineCodes;

    private List<TravelerPricing> travelerPricings;
}
