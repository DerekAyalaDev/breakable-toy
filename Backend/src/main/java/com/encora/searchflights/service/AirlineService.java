package com.encora.searchflights.service;

import com.encora.searchflights.model.AirlineInfo;

public interface AirlineService {
    AirlineInfo getAirlineInfo(String airlineCode);
}
