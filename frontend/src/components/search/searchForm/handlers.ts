import { NavigateFunction } from "react-router-dom";
import { SearchContextState } from "../../../context/search/types";
import { fetchFlightOffers } from "./api";
import { FlightOfferResponse } from "../../../context/flightOffers/types";

export const handleSearch = async (
  event: React.FormEvent<HTMLFormElement>,
  searchValues: Partial<SearchContextState>,
  setFlightData: (data: FlightOfferResponse) => void,
  navigate: NavigateFunction
) => {
  event.preventDefault();

  if (!searchValues.departureAirport || !searchValues.arrivalAirport) {
    console.error("Both departure and arrival airports must be selected.");
    return;
  }

  if (!searchValues.departureDate) {
    console.error("Departure date must be selected.");
    return;
  }

  if (
    searchValues.arrivalDate &&
    searchValues.departureDate.isAfter(searchValues.arrivalDate)
  ) {
    console.error("Arrival date must be after departure date.");
    return;
  }

  try {
    const response = await fetchFlightOffers(searchValues);
    setFlightData(response);
    navigate("/offers");
  } catch (error) {
    console.error("Error during flight search:", error);
  }
};
