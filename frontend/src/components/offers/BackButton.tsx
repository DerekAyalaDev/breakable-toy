import { Box, Button } from "@mui/material";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import { useNavigate } from "react-router-dom";

export const BackButton = ({ to = "/", label = "Return to Search" }) => {
  const navigate = useNavigate();

  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "flex-start",
        width: "fit-content",
      }}
    >
      <Button
        variant="contained"
        startIcon={<ArrowBackIcon />}
        onClick={() => navigate(to)}
        sx={{
          backgroundColor: "var(--secondary-color)",
          color: "#FFFFFF",
          fontWeight: 600,
          padding: "0.5rem 1.5rem",
          minWidth: "fit-content",
          borderRadius: "0.5rem",
          "&:hover": {
            backgroundColor: "var(--dark-secondary-color)",
          },
          transition: "background-color 0.3s ease",
          textTransform: "none",
          display: "flex",
          alignItems: "center",
          gap: "0.5rem", //
          marginBottom: "1rem",
        }}
      >
        {label}
      </Button>
    </Box>
  );
};
