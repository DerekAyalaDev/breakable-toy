import { Box, FormControl, MenuItem, Select } from "@mui/material";
import { FormLabel } from "../FormLabel";

interface NumberOfAdultsSelectorProps {
  label: string;
  numberOfAdults: number;
  onNumberOfAdultsChange: (newValue: number) => void;
}

export const NumberOfAdultsSelector = ({
  label,
  numberOfAdults,
  onNumberOfAdultsChange,
}: NumberOfAdultsSelectorProps) => {
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
            maxWidth: "100px",
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
            value={numberOfAdults}
            onChange={(e) => onNumberOfAdultsChange(Number(e.target.value))}
            displayEmpty
            sx={{
              color: "inherit",
              fontStyle: "normal",
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
            {[...Array(9).keys()].map((num) => (
              <MenuItem key={num + 1} value={num + 1}>
                {num + 1}
              </MenuItem>
            ))}
          </Select>
        </FormControl>
      </Box>
    </Box>
  );
};
