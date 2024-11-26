import { NavigateFunction } from "react-router-dom";
import { SearchContextState } from "../../../context/search/types";
import { fetchFlightOffers } from "../../../apis/fetchFlightOffers";
import { FlightOfferResponse } from "../../../context/flightOffers/types";

export const handleSearch = async (
  event: React.FormEvent<HTMLFormElement>,
  searchValues: Partial<SearchContextState>,
  setFlightData: (data: FlightOfferResponse) => void,
  navigate: NavigateFunction,
  validateInputs: () => boolean
) => {
  event.preventDefault();

  // Validate inputs before proceeding
  if (!validateInputs()) {
    console.error("Validation failed. Please correct the highlighted fields.");
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
