import { Container } from "@mui/material";
import { Navbar } from "../components/navbar/AppBar";
import { useFlightContext } from "../context/flightOffers/FlightsContext";
import { BackButton } from "../components/offers/BackButton";

export const FlightOffers = () => {
  const { flightData } = useFlightContext();

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
      </Container>
    </>
  );
};
