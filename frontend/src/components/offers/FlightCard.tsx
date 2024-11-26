import { Box, Divider } from "@mui/material";
import { FlightOffer } from "../../context/flightOffers/types";
import { FlightInfoSection } from "./FlightInfoSection";
import { PriceSection } from "./PriceSection";
import { ViewDetailsButton } from "./ViewDetailsButton";

interface FlightCardProps {
  offer: FlightOffer;
}

export const FlightCard = ({ offer }: FlightCardProps) => {
  const { itineraries, price, travelerPricings } = offer;

  const totalPrice = `${price.total} ${price.currency}`;
  const pricePerTraveler = `${travelerPricings[0]?.price.total} ${price.currency}`;

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: { xs: "column", md: "row" },
        backgroundColor: "#FFFFFF",
        borderRadius: "0.5rem",
        boxShadow: "0px 4px 6px rgba(0, 0, 0, 0.1)",
        overflow: "hidden",
        marginBottom: "1rem",
      }}
    >
      <Box
        sx={{
          flex: 2,
          padding: "1rem",
          display: "flex",
          flexDirection: "column",
          gap: "0.5rem",
        }}
      >
        <FlightInfoSection itinerary={itineraries[0]} />

        {itineraries.length > 1 && <Divider sx={{ marginY: "1rem" }} />}

        {itineraries.length > 1 && (
          <FlightInfoSection itinerary={itineraries[1]} />
        )}

        <ViewDetailsButton
          onClick={() => console.log(`Viewing details for offer: ${offer.id}`)}
        />
      </Box>

      <PriceSection
        totalPrice={totalPrice}
        pricePerTraveler={pricePerTraveler}
      />
    </Box>
  );
};
