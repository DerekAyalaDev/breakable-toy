package com.encora.searchflights.model.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Itinerary {

    @JsonProperty("duration")
    private String totalDuration; // Total duration of the itinerary (from departure to arrival)

    private List<Segment> segments; // Contains each segment (leg of the journey)
}
