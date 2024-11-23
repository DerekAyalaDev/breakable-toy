import { Autocomplete, Box, InputAdornment, TextField } from "@mui/material";
import React, { useState } from "react";
import { FormLabel } from "../FormLabel";
import { AirportInfo, AirportInputProps } from "./types";
import { handleInputChange } from "./handlers";

export const AirportInput = ({
  label,
  icon,
  airport,
  onAirportChange,
}: AirportInputProps) => {
  const [options, setOptions] = useState<AirportInfo[]>([]);

  return (
    <Box
      sx={{
        marginBottom: "1rem",
        display: "flex",
        alignItems: "center",
        gap: "1rem",
      }}
    >
      <FormLabel label={label} />
      <Autocomplete
        freeSolo
        options={options}
        value={airport}
        getOptionLabel={(option) =>
          typeof option === "string"
            ? option
            : `${option.iataCode} - ${option.address.countryName} - ${option.name}`
        }
        isOptionEqualToValue={(option, value) =>
          typeof value === "string"
            ? option.iataCode === value
            : option.iataCode === value?.iataCode
        }
        onInputChange={(event, value) => {
          if (
            airport &&
            typeof airport === "object" &&
            `${airport.iataCode} - ${airport.address.countryName} - ${airport.name}` !==
              value
          ) {
            onAirportChange(null);
          }
          handleInputChange(
            value,
            setOptions,
            typeof airport === "object" ? airport : null
          );
        }}
        onChange={(event, newValue) =>
          onAirportChange(
            newValue && typeof newValue !== "string" ? newValue : null
          )
        }
        renderInput={(params) => (
          <TextField
            {...params}
            label={label.split(" ")[0]}
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
              },
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
