package com.encora.searchflights.dto;

import com.encora.searchflights.model.Segment;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class FlightDetailDTO {
    private List<Segment> segments;
    private PriceBreakdownDTO priceBreakdown;
    private Duration totalDuration;
    private LocalDateTime initialDepartureDateTime;
    private LocalDateTime finalArrivalDateTime;
}
