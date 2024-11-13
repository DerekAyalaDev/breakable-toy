package com.encora.searchflights.model.flights;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FlightOfferResponse {
    private List<FlightOffer> data;
}
