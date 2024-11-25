import { SearchContextState } from "../../../context/search/types";
import { fetchFlightOffers } from "./api";

export const handleSearch = async (searchValues: Partial<SearchContextState>) => {
  // Validate departure and arrival airports
  if (!searchValues.departureAirport || !searchValues.arrivalAirport) {
    console.error("Both departure and arrival airports must be selected.");
    return;
  }

  // Validate departure date
  if (!searchValues.departureDate) {
    console.error("Departure date must be selected.");
    return;
  }

  // Optional: Add more validations (e.g., ensure arrival date is after departure date)
  if (
    searchValues.arrivalDate &&
    searchValues.departureDate.isAfter(searchValues.arrivalDate)
  ) {
    console.error("Arrival date must be after departure date.");
    return;
  }

  try {
    const response = await fetchFlightOffers(searchValues);
    console.log("Flight search result:", response);
  } catch (error) {
    console.error("Error during flight search:", error);
  }
};
