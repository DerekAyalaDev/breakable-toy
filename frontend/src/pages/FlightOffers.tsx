import { Container } from "@mui/material";
import { Navbar } from "../components/navbar/AppBar";
import { useFlightContext } from "../context/flightOffers/FlightsContext";
import { BackButton } from "../components/offers/BackButton";
import { handlePageChange } from "../components/offers/pagination/handlers";
import { useSearchContext } from "../context/search/SearchContext";
import { Pagination } from "../components/offers/pagination/Pagination";

export const FlightOffers = () => {
  const { flightData, setFlightData } = useFlightContext();
  const { pageNumber, setSearchValues, ...searchValues } = useSearchContext();

  const onPageChange = async (page: number) => {
    await handlePageChange(page, pageNumber, searchValues, setSearchValues, setFlightData);
  };

  return (
    <>
      <Navbar />
      <Container
        maxWidth="md"
        sx={{
          marginTop: "2rem",
          marginBottom: "4rem",
          display: "flex",
          flexDirection: "column",
          justifyContent: "flex-start",
        }}
      >
        <BackButton to="/" label="Return to Search" />
        {flightData && flightData.totalPages > 1 && (
          <Pagination
            totalPages={flightData.totalPages}
            currentPage={pageNumber}
            onPageChange={onPageChange}
          />
        )}
      </Container>
    </>
  );
};
