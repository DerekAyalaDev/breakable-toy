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
  if (pageNumber === currentPage) return;
  setSearchValues("pageNumber", pageNumber);
  try {
    const response = await fetchFlightOffers({ ...searchValues, pageNumber });
    setFlightData(response);
  } catch (error) {
    console.error("Error while fetching flight offers:", error);
  }
};
