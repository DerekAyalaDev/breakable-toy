package com.encora.searchflights.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FareDetailDTO {

    private String cabinClass;
    private String fareClass;
    private List<AmenityDTO> amenities;
}
