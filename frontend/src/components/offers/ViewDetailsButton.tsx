import { Box, Button } from "@mui/material";

interface ViewDetailsButtonProps {
  onClick?: () => void; // Prop opcional para manejar acciones al hacer clic
}

export const ViewDetailsButton = ({ onClick }: ViewDetailsButtonProps) => {
  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: { xs: "center", md: "flex-start" },
        marginTop: "1rem",
      }}
    >
      <Button
        variant="contained"
        onClick={onClick}
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
