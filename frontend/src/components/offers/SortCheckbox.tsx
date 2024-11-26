import { Box, Checkbox } from "@mui/material";
import { useSearchContext } from "../../context/search/SearchContext";
import { useFlightContext } from "../../context/flightOffers/FlightsContext";
import { fetchFlightOffers } from "../../apis/fetchFlightOffers";

interface SortCheckboxProps {
  label: string;
  contextKey: "sortByPrice" | "sortByDuration";
}

export const SortCheckbox = ({ label, contextKey }: SortCheckboxProps) => {
  const {
    [contextKey]: value,
    setSearchValues,
    ...searchValues
  } = useSearchContext();
  const { setFlightData } = useFlightContext();

  const handleChange = async () => {
    const isChecked = !value; // Toggle the value

    setSearchValues(contextKey, isChecked);

    const flightData = await fetchFlightOffers({
      ...searchValues,
      [contextKey]: isChecked,
    });
    setFlightData(flightData);
  };

  return (
    <Box
      sx={{
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        padding: "0.5rem 1rem",
        borderRadius: "8px",
        backgroundColor: "var(--secondary-color)",
        cursor: "pointer",
        "&:hover": {
          backgroundColor: "var(--dark-secondary-color)",
        },
        transition: "background-color 0.3s ease",
        color: "#FFFFFF",
      }}
      onClick={handleChange}
    >
      <Checkbox
        checked={value}
        onChange={handleChange}
        sx={{
          color: "#FFFFFF",
          "&.Mui-checked": {
            color: "#FFFFFF",
          },
          padding: "0",
        }}
      />
      <Box
        component="span"
        sx={{
          marginLeft: "0.5rem",
          fontWeight: 600,
          fontSize: "0.875rem",
        }}
      >
        {label}
      </Box>
    </Box>
  );
};
