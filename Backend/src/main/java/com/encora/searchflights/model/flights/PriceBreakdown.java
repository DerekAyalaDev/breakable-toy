package com.encora.searchflights.model.flights;

import com.encora.searchflights.enums.Currency;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class PriceBreakdown {

    private Currency currency; // Currency code (e.g., "USD", "EUR")

    private double total; // Total price of the flight

    private double base; // Base price of the flight

    private List<Fee> fees; // List of fees associated with the flight
}
