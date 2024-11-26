package com.encora.searchflights.model.flights;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Segment {
    private FlightEndPoint departure;
    private FlightEndPoint arrival;
    private String carrierCode;
    private String carrierName;
    private String number;
    private Aircraft aircraft;
    private OperatingFlight operating;
    private String duration;
    private String id;
    private int numberOfStops;
}
