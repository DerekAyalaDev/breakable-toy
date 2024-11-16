package com.encora.searchflights.model.flights;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Itinerary {
    private String duration; // e.g., "PT16H35M"
    private List<Segment> segments;
}
