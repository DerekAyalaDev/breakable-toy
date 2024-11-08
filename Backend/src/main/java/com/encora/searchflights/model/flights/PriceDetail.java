package com.encora.searchflights.model.flights;

import com.encora.searchflights.enums.Currency;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PriceDetail {

    private Currency currency; // Currency code

    private double total; // Total price for the traveler

    private double base;  // Base price for the traveler
}
