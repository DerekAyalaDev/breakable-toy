package com.encora.searchflights.model.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Segment {

    private FlightEndPoint departure; // Departure details (airport code, time)
    private FlightEndPoint arrival;   // Arrival details (airport code, time)

    @JsonProperty("carrierCode")
    private String airlineCode; // Main airline code

    private String number; // Flight number

    @JsonProperty("operating")
    private OperatingFlight operatingAirline; // Operating airline details if different

    private String duration; // Duration of this segment
}
