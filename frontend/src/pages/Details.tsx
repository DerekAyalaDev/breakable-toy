import { Box, Container } from "@mui/material";
import { Navbar } from "../components/navbar/AppBar";
import { BackButton } from "../components/offers/BackButton";
import { useSelectedOfferContext } from "../context/selectedOffer/SelectedOfferContext";

export const Details = () => {
  const { selectedOffer } = useSelectedOfferContext();
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
        <Box>
          <BackButton to="/offers" label="Return to Offers" />
        </Box>
      </Container>
    </>
  );
};
