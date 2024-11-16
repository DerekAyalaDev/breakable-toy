package com.encora.searchflights.model.flights;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fee {
    private String amount; // Fee amount
    private String type; // Type of fee (e.g., SUPPLIER, TICKETING)
}
