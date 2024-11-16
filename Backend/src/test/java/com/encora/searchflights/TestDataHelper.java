package com.encora.searchflights;

import com.encora.searchflights.enums.Currency;
import com.encora.searchflights.model.airport.AirportInfo;
import com.encora.searchflights.model.dto.FlightSearchRequestDTO;

import java.time.LocalDate;

public class TestDataHelper {
    // Common JSON response for flight search tests
    public static String getFlightSearchJsonResponse() {
        return """
        {
            "data": [
                {
                    "id": "1",
                    "oneWay": false,
                    "itineraries": [
                        {
                            "duration": "PT6H30M"
                        }
                    ],
                    "price": {
                        "grandTotal": "300.00"
                    }
                },
                {
                    "id": "2",
                    "oneWay": false,
                    "itineraries": [
                        {
                            "duration": "PT7H30M"
                        }
                    ],
                    "price": {
                        "grandTotal": "300.00"
                    }
                },
                {
                    "id": "3",
                    "oneWay": false,
                    "itineraries": [
                        {
                            "duration": "PT5H45M"
                        }
                    ],
                    "price": {
                        "grandTotal": "250.00"
                    }
                }
            ]
        }
        """;
    }

    // Helper method to configure FlightSearchRequestDTO with options for sorting by price and duration
    public static FlightSearchRequestDTO getFlightSearchRequestDTO(boolean byPrice, boolean byDuration, LocalDate returnDate) {
        FlightSearchRequestDTO requestDTO = new FlightSearchRequestDTO();
        requestDTO.setDepartureAirportCode("JFK");
        requestDTO.setArrivalAirportCode("LAX");
        requestDTO.setDepartureDate(LocalDate.of(2024, 12, 1));
        requestDTO.setReturnDate(returnDate);
        requestDTO.setNumberOfAdults(1);
        requestDTO.setCurrency(Currency.USD);
        requestDTO.setNonStop(false);
        requestDTO.setPageNumber(1);
        requestDTO.setSortByPrice(byPrice);
        requestDTO.setSortByDuration(byDuration);
        return requestDTO;
    }

    public static AirportInfo createAirportInfo(String name, String iataCode, String city, String country) {
        AirportInfo airportInfo = new AirportInfo();
        airportInfo.setName(name);
        airportInfo.setIataCode(iataCode);

        AirportInfo.Address address = new AirportInfo.Address();
        address.setCity(city);
        address.setCountry(country);

        airportInfo.setAddress(address);
        return airportInfo;
    }

}
