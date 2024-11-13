package com.encora.searchflights.model.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Itinerary {

    private String duration; // e.g., "PT16H35M"

    private List<Segment> segments;
}
