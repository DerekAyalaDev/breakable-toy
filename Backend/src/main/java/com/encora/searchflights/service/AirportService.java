package com.encora.searchflights.service;

import com.encora.searchflights.model.AirportInfo;

import java.util.List;

public interface AirportService {
    List<AirportInfo> searchAirportsByName(String keyword);
}
