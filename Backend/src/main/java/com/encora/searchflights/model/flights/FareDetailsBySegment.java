package com.encora.searchflights.model.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FareDetailsBySegment {

    private String segmentId;

    private String cabin;

    private String fareBasis;

    @JsonProperty("class")
    private String fareClass;

    private IncludedCheckedBags includedCheckedBags;
}
