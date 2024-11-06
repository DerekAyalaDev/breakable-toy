package com.encora.searchflights.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public class StopInfo {
    private AirportInfo airport;
    private Duration layoverDuration;
}
