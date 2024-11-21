import { Box } from "@mui/material";
import { AirportInput } from "../airportInput/AirportInput";
import { FlightLand, FlightTakeoff } from "@mui/icons-material";
import { DateInput } from "../DateInput";
import { CurrencySelector } from "../currencySelector/CurrencySelector";
import { useState } from "react";
import { NonStopCheckbox } from "../NonStopCheckbox";
import { SearchButton } from "../SerachButton";

export const SearchForm = () => {
  const [currency, setCurrency] = useState<string>("");
  const [nonStop, setNonStop] = useState(false);

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
      <CurrencySelector
        label="Currency"
        currency={currency}
        onChange={setCurrency}
      />
      <NonStopCheckbox
        label="Non-Stop"
        checked={nonStop}
        onChange={setNonStop}
      />
      <SearchButton />
    </Box>
  );
};
