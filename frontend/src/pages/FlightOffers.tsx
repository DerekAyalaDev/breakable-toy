import { Box, Container, Typography } from "@mui/material";
import { Navbar } from "../components/navbar/AppBar";
import { useFlightContext } from "../context/flightOffers/FlightsContext";
import { BackButton } from "../components/offers/BackButton";
import { handlePageChange } from "../components/offers/pagination/handlers";
import { useSearchContext } from "../context/search/SearchContext";
import { Pagination } from "../components/offers/pagination/Pagination";
import { useEffect } from "react";
import { fetchFlightOffers } from "../apis/fetchFlightOffers";
import { FlightCard } from "../components/offers/FlightCard";
import { SortCheckbox } from "../components/offers/SortCheckbox";

export const FlightOffers = () => {
  const { flightData, setFlightData } = useFlightContext();
  const { pageNumber, setSearchValues, ...searchValues } = useSearchContext();

  useEffect(() => {
    if (!flightData) {
      fetchFlightOffers(searchValues)
        .then((data) => setFlightData(data))
        .catch((error) =>
          console.error("Error fetching flight offers on initial load:", error)
        );
    }
  }, [flightData, searchValues, setFlightData]);

  const onPageChange = async (page: number) => {
    await handlePageChange(
      page,
      pageNumber,
      searchValues,
      setSearchValues,
      setFlightData
    );
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
        <Box
          sx={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            marginBottom: "1rem",
          }}
        >
          <BackButton to="/" label="Return to Search" />
          <Box sx={{ display: "flex", gap: "1rem" }}>
            <SortCheckbox label="Sort by Price" contextKey="sortByPrice" />
            <SortCheckbox
              label="Sort by Duration"
              contextKey="sortByDuration"
            />
          </Box>
        </Box>
        {flightData ? (
          <>
            {/* Lista de FlightCards */}
            {flightData.offers.map((offer, index) => (
              <FlightCard key={index} offer={offer} dictionaries={flightData.dictionaries}/>
            ))}

            {/* Paginación */}
            {flightData.totalPages > 1 && (
              <Pagination
                totalPages={flightData.totalPages}
                currentPage={pageNumber}
                onPageChange={onPageChange}
              />
            )}
          </>
        ) : (
          <Box sx={{ textAlign: "center", marginTop: "2rem" }}>
            <Typography variant="h6" color="text.secondary">
              No flight offers found.
            </Typography>
          </Box>
        )}
      </Container>
    </>
  );
};
