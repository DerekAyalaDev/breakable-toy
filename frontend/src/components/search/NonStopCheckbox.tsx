import { Box, Checkbox } from "@mui/material";
import { FormLabel } from "./FormLabel";

interface NonStopCheckboxProps {
  label: string;
  checked: boolean;
  onChange: (checked: boolean) => void;
}

export const NonStopCheckbox = ({
  label,
  checked,
  onChange,
}: NonStopCheckboxProps) => {
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
          display: "flex",
          alignItems: "center",
        }}
      >
        <Checkbox
          checked={checked}
          onChange={(event) => onChange(event.target.checked)}
          sx={{
            padding: 0,
            marginLeft: "-4px",
            "& .MuiSvgIcon-root": {
              color: "var(--secondary-color)",
              fontSize: "1.5rem",
              transition: "color 0.3s ease",
            },
            "&:hover .MuiSvgIcon-root": {
              color: "var(--primary-color)",
            },
            "&.Mui-checked .MuiSvgIcon-root": {
              color: "var(--dark-primary-color)",
            },
          }}
        />
      </Box>
    </Box>
  );
};
