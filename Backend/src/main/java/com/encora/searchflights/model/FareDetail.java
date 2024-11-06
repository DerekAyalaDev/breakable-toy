package com.encora.searchflights.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FareDetail {
    private String cabinClass;
    private String fareClass;
    private List<Amenity> amenities;
}
