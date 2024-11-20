import { Autocomplete, Box, InputAdornment, TextField, Typography } from "@mui/material";
import React from "react";

interface AirportInputProps {
  label: string;
  icon: React.ReactNode;
}

export const AirportInput = ({ label, icon }: AirportInputProps) => {
  const [keyWord] = label.split(" ");
  return (
    <Box
      sx={{
        marginBottom: "1rem",
        display: "flex",
        alignItems: "center",
        gap: "1rem"
      }}
    >
      <Typography
        variant="subtitle1"
        component="label"
        sx={{
          color: "var(--dark-secondary-color)",
          fontWeight: 600,
          flex: "1",
          minWidth: "80px",
          maxWidth: "130px",
          textAlign: "end"
        }}
      >
        {label}
      </Typography>
      <Autocomplete
        freeSolo
        options={[]}
        renderInput={(params) => (
          <TextField
            {...params}
            label={keyWord}
            InputProps={{
              ...params.InputProps,
              startAdornment: (
                <InputAdornment position="start">
                  {React.cloneElement(icon as React.ReactElement, {
                    sx: { color: "var(--primary-color)", fontSize: "1.5rem" },
                  })}
                </InputAdornment>
              ),
            }}
            InputLabelProps={{
              sx: {
                color: "var(--dark-secondary-color)",
                fontWeight: 500,
              }
            }}
            variant="outlined"
            fullWidth
            sx={{
              "& .MuiOutlinedInput-root": {
                "& fieldset": {
                  borderColor: "var(--secondary-color)",
                  transition: "border-color 0.3s ease",
                },
                "&:hover fieldset": {
                  borderColor: "var(--primary-color)",
                },
                "&.Mui-focused fieldset": {
                  borderColor: "var(--dark-primary-color)",
                },
              },
              "&:hover .MuiInputLabel-root": {
                color: "var(--primary-color)",
              },
              "&.Mui-focused .MuiInputLabel-root": {
                color: "var(--dark-primary-color)",
              },
            }}
          />
        )}
        sx={{
          flex: "3",
        }}
      />
    </Box>
  );
};
