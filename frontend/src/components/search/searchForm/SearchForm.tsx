import { Box } from "@mui/material";
import { AirportInput } from "../airportInput/AirportInput";
import { FlightLand, FlightTakeoff } from "@mui/icons-material";
import { DateInput } from "../DateInput";
import { CurrencySelector } from "../currencySelector/CurrencySelector";
import { useState } from "react";
import { NonStopCheckbox } from "../nonStopCheckbox/NonStopCheckbox";
import { SearchButton } from "../SerachButton";
import { Dayjs } from "dayjs";

export const SearchForm = () => {
  const [departureAirport, setDepartureAirport] = useState<string>("");
  const [arrivalAirport, setArrivalAirport] = useState<string>("");
  const [departureDate, setDepartureDate] = useState<Dayjs | null>(null);
  const [arrivalDate, setArrivalDate] = useState<Dayjs | null>(null);
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
      <AirportInput
        label="Departure Airport"
        airport={departureAirport}
        onAirportChange={setDepartureAirport}
        icon={<FlightTakeoff />}
      />
      <AirportInput
        label="Arrival Airport"
        airport={arrivalAirport}
        onAirportChange={setArrivalAirport}
        icon={<FlightLand />}
      />
      <DateInput
        label="Departure Date"
        date={departureDate}
        onDateChange={setDepartureDate}
      />
      <DateInput
        label="Arrival Date"
        date={arrivalDate}
        onDateChange={setArrivalDate}
      />
      <CurrencySelector
        label="Currency"
        currency={currency}
        onCurrencyChange={setCurrency}
      />
      <NonStopCheckbox
        label="Non-Stop"
        checked={nonStop}
        onCheckedChange={setNonStop}
      />
      <SearchButton />
    </Box>
  );
};
