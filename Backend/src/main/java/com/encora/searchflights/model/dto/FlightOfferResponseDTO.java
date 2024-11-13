package com.encora.searchflights.model.dto;

import com.encora.searchflights.model.flights.FlightOffer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FlightOfferResponseDTO {
    private List<FlightOffer> offers;
    private int totalPages;
}
