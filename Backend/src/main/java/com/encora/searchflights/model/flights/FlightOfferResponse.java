package com.encora.searchflights.model.flights;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightOfferResponse {
    private List<FlightOffer> data;
    private Dictionaries dictionaries;
}
