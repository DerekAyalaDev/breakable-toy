import { Box, FormControl, MenuItem, Select, Typography } from "@mui/material";
import { FormLabel } from "../FormLabel";
import { CurrencySelectorProps } from "./types";

export const CurrencySelector = ({
  label,
  currency,
  onCurrencyChange,
  error,
  helperText,
}: CurrencySelectorProps & { error?: boolean; helperText?: string }) => {
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
          error={error} // Error state for red outline
          sx={{
            maxWidth: "250px",
            "& .MuiOutlinedInput-root": {
              "& fieldset": {
                borderColor: error ? "red" : "var(--secondary-color)",
                transition: "border-color 0.3s ease",
              },
              "&:hover fieldset": {
                borderColor: error ? "red" : "var(--primary-color)",
              },
              "&.Mui-focused fieldset": {
                borderColor: error ? "red" : "var(--dark-primary-color)",
              },
            },
            "& .MuiSelect-icon": {
              color: error ? "red" : "var(--dark-secondary-color)",
              transition: "color 0.3s ease",
            },
            "&:hover .MuiSelect-icon": {
              color: error ? "red" : "var(--primary-color)",
            },
            "&.Mui-focused .MuiSelect-icon": {
              color: error ? "red" : "var(--dark-primary-color)",
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
          {helperText && (
            <Typography
              variant="caption"
              color={error ? "red" : "textSecondary"}
              sx={{ marginLeft: "14px" }}
            >
              {helperText}
            </Typography>
          )}
        </FormControl>
      </Box>
    </Box>
  );
};
