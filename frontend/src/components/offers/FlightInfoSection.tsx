import { Box, Typography } from "@mui/material";
import { Itinerary } from "../../context/flightOffers/types";
import { useSearchContext } from "../../context/search/SearchContext";
import dayjs from "dayjs";

interface FlightInfoSectionProps {
  itinerary: Itinerary;
}

export const FlightInfoSection = ({ itinerary }: FlightInfoSectionProps) => {
  const { departureAirport, arrivalAirport } = useSearchContext();
  const { segments, duration } = itinerary;

  const departureTime = segments[0].departure.at;
  const arrivalTime = segments[segments.length - 1].arrival.at;

  const departureAirportInfo = `${departureAirport?.name} (${departureAirport?.iataCode})`;
  const arrivalAirportInfo = `${arrivalAirport?.name} (${arrivalAirport?.iataCode})`;

  const airlines = Array.from(
    new Set(
      segments.map(
        (segment) =>
          `${segment.carrierName} (${segment.carrierCode})${
            segment.operating?.carrierName &&
            segment.operating.carrierName !== segment.carrierName
              ? `, ${segment.operating.carrierName} (${segment.operating.carrierCode})`
              : ""
          }`
      )
    )
  ).join(", ");

  const formattedTimes = formatFlightTimes(departureTime, arrivalTime);

  // Calcular escalas
  const layovers =
    segments.length > 1
      ? segments.slice(0, -1).map((segment, index) => {
          const nextSegment = segments[index + 1];
          const layoverTime = calculateLayoverTime(
            segment.arrival.at,
            nextSegment.departure.at
          );
          return {
            airport: `${segment.arrival.iataCode} `,
            layoverTime,
          };
        })
      : [];

  return (
    <Box sx={{ display: "flex", flexDirection: "row", gap: "1rem" }}>
      {/* Columna 1: Aeropuertos, horarios y aerolíneas */}
      <Box sx={{ flex: 2 }}>
        <Typography variant="h6" fontWeight={700}>
          {departureAirportInfo}
        </Typography>
        <Typography variant="h6" fontWeight={700}>
          {arrivalAirportInfo}
        </Typography>
        <Typography variant="body1" fontWeight={500}>
          {formattedTimes.formattedDeparture} →{" "}
          {formattedTimes.formattedArrival}
          {formattedTimes.isNextDay && (
            <Typography variant="caption" color="textSecondary">
              {" "}
              (+1)
            </Typography>
          )}
        </Typography>
        <Typography variant="body2" color="text.secondary" fontWeight={500}>
          Airlines: {airlines}
        </Typography>
      </Box>

      {/* Columna 2: Duración y escalas */}
      <Box sx={{ flex: 1 }}>
        <Typography variant="h6" fontWeight={600}>
          Total Duration:
        </Typography>
        <Typography variant="h6" fontWeight={500}>
          {formatDuration(duration)}
        </Typography>
        {layovers.length > 0 && (
          <>
            <Typography variant="body2" fontWeight={600}>
              Layovers:
            </Typography>
            {layovers.map((layover, index) => (
              <Typography key={index} variant="body2" color="text.secondary">
                {layover.airport}: {layover.layoverTime}
              </Typography>
            ))}
          </>
        )}
      </Box>
    </Box>
  );
};

const calculateLayoverTime = (
  arrivalTime: string,
  nextDepartureTime: string
) => {
  const arrival = new Date(arrivalTime);
  const nextDeparture = new Date(nextDepartureTime);

  const diffMs = nextDeparture.getTime() - arrival.getTime();
  const hours = Math.floor(diffMs / (1000 * 60 * 60));
  const minutes = Math.floor((diffMs % (1000 * 60 * 60)) / (1000 * 60));

  return `${hours}h ${minutes}m`;
};

const formatDuration = (isoDuration: string): string => {
  const match = isoDuration.match(/PT(\d+H)?(\d+M)?/);
  if (!match) return isoDuration; // Si no coincide con el formato esperado, retornar tal cual

  const hours = match[1] ? match[1].replace("H", "") : "0";
  const minutes = match[2] ? match[2].replace("M", "") : "0";

  return `${hours}h ${minutes}m`;
};

export const formatFlightTimes = (departure: string, arrival: string) => {
  const departureTime = dayjs(departure);
  const arrivalTime = dayjs(arrival);

  const formattedDeparture = departureTime.format("HH:mm");
  const formattedArrival = arrivalTime.format("HH:mm");

  const isNextDay = arrivalTime.isAfter(departureTime, "day");

  return {
    formattedDeparture,
    formattedArrival,
    isNextDay,
  };
};
