package com.encora.searchflights.model.flights;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Dictionaries {
    private Map<String, Location> locations;
    private Map<String, String> aircraft;
    private Map<String, String> currencies;
    private Map<String, String> carriers;
}