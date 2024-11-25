import { Box, Button } from "@mui/material";


export const SearchButton = () => {
  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "flex-end",
      }}
    >
      <Button
        type="submit"
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
      >
        Search
      </Button>
    </Box>
  );
};
