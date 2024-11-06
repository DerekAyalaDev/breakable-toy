package com.encora.searchflights.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public class StopInfoDTO {
    private AirportInfoDTO airport;
    private Duration layoverDuration;
}
