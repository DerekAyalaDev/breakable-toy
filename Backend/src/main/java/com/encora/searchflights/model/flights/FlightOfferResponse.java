package com.encora.searchflights.model.flights;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FlightOfferResponse {
    private List<FlightOffer> data;
    private Meta meta;

    @Getter
    @Setter
    public static class Meta {
        private int count;
        private Links links;
    }

    @Getter
    @Setter
    public static class Links {
        private String self;
    }
}
