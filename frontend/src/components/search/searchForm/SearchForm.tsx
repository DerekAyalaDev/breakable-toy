import { Box } from "@mui/material";
import { AirportInput } from "../airportInput/AirportInput";
import { FlightLand, FlightTakeoff } from "@mui/icons-material";
import { DateInput } from "../dateInput/DateInput";
import { CurrencySelector } from "../currencySelector/CurrencySelector";
import { NonStopCheckbox } from "../nonStopCheckbox/NonStopCheckbox";
import { SearchButton } from "../SearchButton";
import { NumberOfAdultsSelector } from "../NumberOfAdultsSelector/NumberOfAdultsSelector";
import { useSearchContext } from "../../../context/search/SearchContext";
import { handleSearch } from "./handlers";
import { useNavigate } from "react-router-dom";

export const SearchForm = () => {
  const {
    departureAirport,
    arrivalAirport,
    departureDate,
    arrivalDate,
    currency,
    numberOfAdults,
    nonStop,
    setSearchValues,
  } = useSearchContext();

  const navigate = useNavigate();

  return (
    <Box
      component="form"
      onSubmit={(event) =>
        handleSearch(event, {
          departureAirport,
          arrivalAirport,
          departureDate,
          arrivalDate,
          currency,
          numberOfAdults,
          nonStop,
        }, navigate)
      }
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
        onAirportChange={(airport) => setSearchValues("departureAirport", airport)}
        icon={<FlightTakeoff />}
      />
      <AirportInput
        label="Arrival Airport"
        airport={arrivalAirport}
        onAirportChange={(airport) => setSearchValues("arrivalAirport", airport)}
        icon={<FlightLand />}
      />
      <DateInput
        label="Departure Date"
        date={departureDate}
        onDateChange={(date) => setSearchValues("departureDate", date)}
      />
      <DateInput
        label="Arrival Date"
        date={arrivalDate}
        onDateChange={(date) => setSearchValues("arrivalDate", date)}
      />
      <CurrencySelector
        label="Currency"
        currency={currency}
        onCurrencyChange={(value) => setSearchValues("currency", value)}
      />
      <NumberOfAdultsSelector
        label="Adults"
        numberOfAdults={numberOfAdults}
        onNumberOfAdultsChange={(value) => setSearchValues("numberOfAdults", value)}
      />
      <NonStopCheckbox
        label="Non-Stop"
        checked={nonStop}
        onCheckedChange={(value) => setSearchValues("nonStop", value)}
      />
      <SearchButton />
    </Box>
  );
};
