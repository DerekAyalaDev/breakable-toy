package com.encora.searchflights.model.flights;

import java.util.Currency;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Price {
    private Currency currency;
    private String total;
    private String base;
    private List<Fee> fees;
    private String grandTotal;
}
