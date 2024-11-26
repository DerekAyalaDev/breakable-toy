import { Box, Container } from "@mui/material";
import { Navbar } from "../components/navbar/AppBar";
import { BackButton } from "../components/offers/BackButton";
import { useSelectedOfferContext } from "../context/selectedOffer/SelectedOfferContext";
import { PriceDetails } from "../components/details/PriceDetails";

export const Details = () => {
  const { selectedOffer } = useSelectedOfferContext();

  const base = selectedOffer?.price.base ? parseFloat(selectedOffer.price.base) : 0;
  const total = selectedOffer?.price.total ? parseFloat(selectedOffer.price.total) : 0;
  const perTraveler = selectedOffer?.travelerPricings[0]?.price.total
    ? parseFloat(selectedOffer.travelerPricings[0].price.total)
    : 0;

  const fees = total - base;

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
        <Box
          sx={{
            display: "flex",
            flexDirection: { xs: "column", md: "row" },
            gap: "2rem",
            padding: "2rem",
          }}
        >
          <Box
            sx={{
              flex: 2,
              border: "1px solid var(--secondary-color)",
              borderRadius: "8px",
              padding: "1rem",
              backgroundColor: "#fff",
            }}
          >
          </Box>

          <PriceDetails
            base={base.toFixed(2)}
            fees={fees.toFixed(2)}
            total={total.toFixed(2)}
            currency={selectedOffer?.price.currency}
            perTraveler={perTraveler.toFixed(2)}
          />
        </Box>
      </Container>
    </>
  );
};
