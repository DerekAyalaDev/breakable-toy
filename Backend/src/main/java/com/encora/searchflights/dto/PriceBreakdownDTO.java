package com.encora.searchflights.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceBreakdownDTO {
    private double basePrice;
    private double totalPrice;
    private double fees;
    private double pricePerTraveler;
    private String currency;
}
