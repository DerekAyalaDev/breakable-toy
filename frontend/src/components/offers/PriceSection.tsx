import { Box, Typography } from "@mui/material";

interface PriceSectionProps {
  totalPrice: string;
  pricePerTraveler: string;
}

export const PriceSection = ({ totalPrice, pricePerTraveler }: PriceSectionProps) => {
  return (
    <Box
      sx={{
        backgroundColor: "var(--secondary-color)",
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
        padding: "1rem",
        textAlign: "center",
      }}
    >
      <Typography variant="h6" fontWeight={600} color="#FFFFFF">
        Total
      </Typography>
      <Typography variant="h4" fontWeight={700} color="#FFFFFF">
        {totalPrice}
      </Typography>
      <Typography variant="body2" fontWeight={500} color="#FFFFFF">
        Per Traveler
      </Typography>
      <Typography variant="body1" fontWeight={600} color="#FFFFFF">
        {pricePerTraveler}
      </Typography>
    </Box>
  );
};
