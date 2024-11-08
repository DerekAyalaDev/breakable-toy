package com.encora.searchflights.service;

import com.encora.searchflights.model.airline.AirlineInfo;

public interface AirlineService {
    AirlineInfo getAirlineInfo(String airlineCode);
}
