import { NavigateFunction } from "react-router-dom";
import { SearchContextState } from "../../../context/search/types";
import { fetchFlightOffers } from "../../../apis/fetchFlightOffers";
import { FlightOfferResponse } from "../../../context/flightOffers/types";

export const handleSearch = async (
  event: React.FormEvent<HTMLFormElement>,
  searchValues: Partial<SearchContextState>,
  setFlightData: (data: FlightOfferResponse) => void,
  navigate: NavigateFunction,
  validateInputs: () => boolean,
  setAlertMessage: (message: string | null) => void // Add alert message handler
) => {
  event.preventDefault();

  // Validate inputs before proceeding
  if (!validateInputs()) {
    setAlertMessage("Please correct the highlighted fields before searching.");
    return;
  }

  try {
    const response = await fetchFlightOffers(searchValues);

    if (!response.offers || response.offers.length === 0) {
      // Show alert if no offers are found
      setAlertMessage("No flight offers found for the selected criteria.");
      return;
    }

    setFlightData(response);
    navigate("/offers");
  } catch (error) {
    console.error("Error during flight search:", error);
    setAlertMessage("An error occurred while searching for flights. Please try again.");
  }
};
