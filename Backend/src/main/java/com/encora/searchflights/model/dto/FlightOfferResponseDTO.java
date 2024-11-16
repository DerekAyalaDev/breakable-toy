package com.encora.searchflights.model.dto;

import java.util.List;

import com.encora.searchflights.model.flights.FlightOffer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightOfferResponseDTO {
    private List<FlightOffer> offers;
    private int totalPages;
}
