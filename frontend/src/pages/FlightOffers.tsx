import { Container } from "@mui/material";
import { Navbar } from "../components/navbar/AppBar";

export const FlightOffers = () => {
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
        <div>Flight Offers</div>
      </Container>
    </>
  );
};
