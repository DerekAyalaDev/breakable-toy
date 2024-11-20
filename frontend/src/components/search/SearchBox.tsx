import { Box, Typography } from "@mui/material";

export const SearchBox = () => {
  return (
    <Box
      sx={{
        backgroundColor: "#FFFFFF",
        padding: "2rem",
        borderRadius: "8px",
        boxShadow: "0px 4px 10px rgba(0, 0, 0, 0.1)",
      }}
    >
      <Typography
        variant="h6"
        component="p"
        sx={{
          textAlign: "center",
          marginBottom: "1rem",
          color: "var(--dark-secondary-color)",
        }}
      >
        Discover exclusive flight deals powered by the Amadeus API. <br />
        Fill out the form below to find flights tailored to your travel plans!
      </Typography>
      <Typography variant="body1" component="div"></Typography>
    </Box>
  );
};
