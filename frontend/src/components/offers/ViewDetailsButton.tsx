import { Box, Button } from "@mui/material";
import { FlightOffer } from "../../context/flightOffers/types";
import { useSelectedOfferContext } from "../../context/selectedOffer/SelectedOfferContext";
import { useNavigate } from "react-router-dom";

interface ViewDetailsButtonProps {
  offer: FlightOffer;
}

export const ViewDetailsButton = ({ offer }: ViewDetailsButtonProps) => {
  const { setSelectedOffer } = useSelectedOfferContext();
  const navigate = useNavigate();

  const handleViewDetails = () => {
    setSelectedOffer(offer);
    navigate("/details");
  };

  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: { xs: "center", md: "flex-start" },
        marginTop: "1rem",
      }}
    >
      <Button
        onClick={handleViewDetails}
        variant="contained"
        sx={{
          backgroundColor: "var(--primary-color)",
          color: "#FFFFFF",
          textTransform: "none",
          padding: "0.5rem 1rem",
          fontSize: "1rem",
          fontWeight: 600,
          "&:hover": {
            backgroundColor: "var(--dark-primary-color)",
          },
        }}
      >
        Details
      </Button>
    </Box>
  );
};
