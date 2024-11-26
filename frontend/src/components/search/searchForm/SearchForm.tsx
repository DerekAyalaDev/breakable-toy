import { Alert, Box } from "@mui/material";
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
import { useFlightContext } from "../../../context/flightOffers/FlightsContext";
import { useEffect, useState } from "react";
import dayjs from "dayjs";

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

  const { setFlightData } = useFlightContext();
  const navigate = useNavigate();

  const [alertMessage, setAlertMessage] = useState<string | null>(null);

  const [errors, setErrors] = useState({
    departureAirport: false,
    arrivalAirport: false,
    departureDate: false,
    arrivalDate: false,
    currency: false,
  });

  const validateInputs = (): boolean => {
    const today = dayjs();

    const newErrors = {
      departureAirport: !departureAirport,
      arrivalAirport: !arrivalAirport,
      departureDate:
        !departureDate ||
        !departureDate.isValid() ||
        departureDate.isBefore(today),
      arrivalDate: arrivalDate
        ? !arrivalDate.isValid() ||
          (departureDate && arrivalDate.isBefore(departureDate))
        : false, // Ensure a boolean value is used
      currency: !currency,
    };

    setErrors({
      ...newErrors,
      arrivalDate: Boolean(newErrors.arrivalDate),
    });

    return !Object.values(newErrors).some((error) => error);
  };

  useEffect(() => {
    setSearchValues("pageNumber", 1);
    setSearchValues("sortByPrice", false);
    setSearchValues("sortByDuration", false);
  }, [setSearchValues]);

  return (
    <Box
      component="form"
      onSubmit={(event) =>
        handleSearch(
          event,
          {
            departureAirport,
            arrivalAirport,
            departureDate,
            arrivalDate,
            currency,
            numberOfAdults,
            nonStop,
          },
          setFlightData,
          navigate,
          validateInputs,
          setAlertMessage
        )
      }
      sx={{
        display: "flex",
        flexDirection: "column",
        gap: "1rem",
        width: "100%",
      }}
    >
      {/* Display Alert */}
      {alertMessage && (
        <Alert
          severity="warning"
          onClose={() => setAlertMessage(null)}
          sx={{
            marginBottom: "1rem",
          }}
        >
          {alertMessage}
        </Alert>
      )}
      <AirportInput
        label="Departure Airport"
        airport={departureAirport}
        onAirportChange={(airport) =>
          setSearchValues("departureAirport", airport)
        }
        icon={<FlightTakeoff />}
        error={errors.departureAirport}
        helperText={
          errors.departureAirport
            ? "Please select a departure airport"
            : undefined
        }
      />
      <AirportInput
        label="Arrival Airport"
        airport={arrivalAirport}
        onAirportChange={(airport) =>
          setSearchValues("arrivalAirport", airport)
        }
        icon={<FlightLand />}
        error={errors.arrivalAirport}
        helperText={
          errors.departureAirport
            ? "Please select a arrival airport"
            : undefined
        }
      />
      <DateInput
        label="Departure Date"
        date={departureDate}
        onDateChange={(date) => setSearchValues("departureDate", date)}
        error={errors.departureDate}
        helperText={
          errors.departureDate
            ? "Please select a valid departure date"
            : undefined
        }
      />
      <DateInput
        label="Arrival Date"
        date={arrivalDate}
        onDateChange={(date) => setSearchValues("arrivalDate", date)}
        error={errors.arrivalDate}
        helperText={
          errors.arrivalDate
            ? "Arrival date cannot be before departure date"
            : undefined
        }
      />
      <CurrencySelector
        label="Currency"
        currency={currency}
        onCurrencyChange={(value) => setSearchValues("currency", value)}
        error={errors.currency}
        helperText={errors.currency ? "Please select a currency" : undefined}
      />
      <NumberOfAdultsSelector
        label="Adults"
        numberOfAdults={numberOfAdults}
        onNumberOfAdultsChange={(value) =>
          setSearchValues("numberOfAdults", value)
        }
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
