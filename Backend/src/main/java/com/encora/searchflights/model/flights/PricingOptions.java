package com.encora.searchflights.model.flights;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PricingOptions {
    private List<String> fareType;
    private boolean includedCheckedBagsOnly;
}
