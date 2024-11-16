package com.encora.searchflights.model.flights;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncludedCheckedBags {
    private int weight; // Weight allowance
    private String weightUnit; // Weight unit (e.g., "KG")
}
