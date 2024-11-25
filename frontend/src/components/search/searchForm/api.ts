import axios from "axios";
import { SearchContextState } from "../../../context/search/types";

export const fetchFlightOffers = async (searchValues: Partial<SearchContextState>) => {
  const {
    departureAirport,
    arrivalAirport,
    departureDate,
    arrivalDate,
    numberOfAdults,
    currency,
    nonStop,
    pageNumber,
    sortByPrice,
    sortByDuration,
  } = searchValues;

  try {
    const response = await axios.get("http://localhost:9090/api/flights/search", {
      params: {
        departureAirportCode: departureAirport?.iataCode, // Extract iataCode
        arrivalAirportCode: arrivalAirport?.iataCode,    // Extract iataCode
        departureDate: departureDate?.format("YYYY-MM-DD"),
        returnDate: arrivalDate?.format("YYYY-MM-DD"),
        numberOfAdults,
        currency,
        nonStop,
        pageNumber,
        sortByPrice,
        sortByDuration,
      },
    });

    return response.data;
  } catch (error) {
    throw error;
  }
};
