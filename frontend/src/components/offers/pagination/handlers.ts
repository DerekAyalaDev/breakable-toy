import { fetchFlightOffers } from "../../../apis/fetchFlightOffers";
import { SearchContextState } from "../../../context/search/types";
import { FlightOfferResponse } from "../../../context/flightOffers/types";

export const handlePageChange = async (
  pageNumber: number,
  currentPage: number,
  searchValues: Partial<SearchContextState>,
  setSearchValues: (key: keyof SearchContextState, value: any) => void,
  setFlightData: (data: FlightOfferResponse) => void
) => {
  try {
    if (pageNumber === currentPage) {
      console.log("Page already selected, skipping fetch.");
      return;
    }

    setSearchValues("pageNumber", pageNumber);

    const response = await fetchFlightOffers({ ...searchValues, pageNumber });

    setFlightData(response);
  } catch (error) {
    console.error("Error while fetching flight offers:", error);
  }
};
