import { Box } from "@mui/material";
import { AirportInput } from "./airportInput/AirportInput";
import { FlightLand, FlightTakeoff } from "@mui/icons-material";
import { DateInput } from "./DateInput";

export const SearchForm = () => {
  return (
    <Box
      component="form"
      sx={{
        display: "flex",
        flexDirection: "column",
        gap: "1rem",
        width: "100%",
      }}
    >
      <AirportInput label="Departure Airport" icon={<FlightTakeoff />} />
      <AirportInput label="Arrival Airport" icon={<FlightLand />} />
      <DateInput label="Departure Date" />
      <DateInput label="Arrival Date" />
    </Box>
  )
}
