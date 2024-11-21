import { Autocomplete, Box, InputAdornment, TextField } from "@mui/material";
import React from "react";
import { FormLabel } from "../FormLabel";
import { AirportInputProps } from "./types";

export const AirportInput = ({ label, icon, airport, onAirportChange }: AirportInputProps) => {
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
      <FormLabel label={label} />
      <Autocomplete
        freeSolo
        value={airport}
        onInputChange={(event, newValue) => onAirportChange(newValue || "")}
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
