package com.encora.searchflights.model.flights;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelerPricing {
    private String travelerId;
    private String fareOption;
    private String travelerType;
    private Price price;
    private List<FareDetailsBySegment> fareDetailsBySegment;
}
