import { Box, Button } from "@mui/material";

interface SearchButtonProps {
  onSubmit: () => void;
}

export const SearchButton = ({ onSubmit }: SearchButtonProps) => {
  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "flex-end",
      }}
    >
      <Button
        variant="contained"
        sx={{
          backgroundColor: "var(--primary-color)",
          color: "#FFFFFF",
          fontWeight: 600,
          padding: "0.5rem 2rem",
          minWidth: "120px",
          "&:hover": {
            backgroundColor: "var(--dark-primary-color)",
          },
          transition: "background-color 0.3s ease",
        }}
        onClick={onSubmit}
      >
        Search
      </Button>
    </Box>
  );
};
