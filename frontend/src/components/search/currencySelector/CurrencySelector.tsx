import { Box, FormControl, MenuItem, Select } from "@mui/material";
import { FormLabel } from "../FormLabel";
import { CurrencySelectorProps } from "./types";

export const CurrencySelector = ({
  label,
  currency,
  onCurrencyChange,
}: CurrencySelectorProps) => {
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
      <Box
        sx={{
          flex: 3,
        }}
      >
        <FormControl
          fullWidth
          sx={{
            maxWidth: "250px",
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
            "& .MuiSelect-icon": {
              color: "var(--dark-secondary-color)",
              transition: "color 0.3s ease",
            },
            "&:hover .MuiSelect-icon": {
              color: "var(--primary-color)",
            },
            "&.Mui-focused .MuiSelect-icon": {
              color: "var(--dark-primary-color)",
            },
          }}
        >
          <Select
            value={currency}
            onChange={(e) => onCurrencyChange(e.target.value)}
            displayEmpty
            sx={{
              color: currency === "" ? "rgba(0, 0, 0, 0.5)" : "inherit",
              fontStyle: currency === "" ? "italic" : "normal",
              "& .MuiSelect-icon": {
                color: "var(--dark-secondary-color)",
              },
              "&:hover .MuiSelect-icon": {
                color: "var(--primary-color)",
              },
              "&.Mui-focused .MuiSelect-icon": {
                color: "var(--dark-primary-color)",
              },
            }}
          >
            <MenuItem
              value=""
              disabled
              sx={{
                color: "rgba(0, 0, 0, 0.5)",
                fontStyle: "italic",
              }}
            >
              Select Currency
            </MenuItem>
            <MenuItem value="USD">USD</MenuItem>
            <MenuItem value="EUR">EUR</MenuItem>
            <MenuItem value="MXN">MXN</MenuItem>
          </Select>
        </FormControl>
      </Box>
    </Box>
  );
};
