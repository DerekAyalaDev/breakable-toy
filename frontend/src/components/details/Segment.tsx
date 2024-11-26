import React from "react";
import { Box, Typography } from "@mui/material";

interface SegmentProps {
  segmentId: string;
  segmentNumber: number;
  type: "outbound" | "return"; // 'outbound' for departure, 'return' for the return flight
  departureAirport: string;
  arrivalAirport: string;
  departureTime: string;
  arrivalTime: string;
  duration: string;
  airlineCode: string;
  flightNumber: string;
  aircraftCode: string;
  cabin: string;
  fareClass: string;
}

export const Segment = ({
  segmentNumber,
  type,
  departureAirport,
  arrivalAirport,
  departureTime,
  arrivalTime,
  duration,
  airlineCode,
  flightNumber,
  aircraftCode,
  cabin,
  fareClass,
}: SegmentProps) => {
  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: { xs: "column", md: "row" },
        gap: "2rem",
        border: "1px solid var(--secondary-color)",
        borderRadius: "8px",
        padding: "1rem",
        backgroundColor: "#fff",
        marginBottom: "1rem",
      }}
    >
      {/* 2/3 Section: Segment Information */}
      <Box
        sx={{
          flex: 2,
        }}
      >
        {/* Segment Number and Type */}
        <Typography variant="h6" fontWeight={600}>
          Segment {segmentNumber} ({type === "outbound" ? "Outbound" : "Return"}
          )
        </Typography>

        {/* Airport Codes */}
        <Typography variant="body1" fontWeight={600}>
          {departureAirport} → {arrivalAirport}
        </Typography>

        {/* Dates */}
        <Typography
          variant="body2"
          sx={{ color: "text.secondary", marginTop: "0.5rem" }}
        >
          <strong>Departure:</strong>{" "}
          {new Date(departureTime).toLocaleString("en-US", {
            month: "short",
            day: "numeric",
            year: "numeric",
            hour: "2-digit",
            minute: "2-digit",
            hour12: true,
          })}
        </Typography>
        <Typography variant="body2" sx={{ color: "text.secondary" }}>
          <strong>Arrival:</strong>{" "}
          {new Date(arrivalTime).toLocaleString("en-US", {
            month: "short",
            day: "numeric",
            year: "numeric",
            hour: "2-digit",
            minute: "2-digit",
            hour12: true,
          })}
        </Typography>

        {/* Duration */}
        <Typography
          variant="body2"
          sx={{ marginTop: "0.5rem", color: "text.secondary" }}
        >
          <strong>Duration:</strong>{" "}
          <Box component="span" sx={{ fontWeight: 400 }}>
            {duration}
          </Box>
        </Typography>

        {/* Airline Code */}
        <Typography
          variant="body2"
          sx={{ marginTop: "0.5rem", color: "text.secondary" }}
        >
          <strong>Airline:</strong>{" "}
          <Box component="span" sx={{ fontWeight: 400 }}>
            {airlineCode}
          </Box>
        </Typography>

        {/* Flight Number */}
        <Typography variant="body2" sx={{ color: "text.secondary" }}>
          <strong>Flight Number:</strong>{" "}
          <Box component="span" sx={{ fontWeight: 400 }}>
            {flightNumber}
          </Box>
        </Typography>
      </Box>

      {/* 1/3 Section: Travelers' Fare Details */}
      <Box
        sx={{
          flex: 1,
          display: "flex",
          flexDirection: "column",
          border: "1px solid var(--secondary-color)",
          borderRadius: "8px",
          padding: "1rem",
          backgroundColor: "#f9f9f9",
        }}
      >
        <Typography variant="h6" fontWeight={500} color="var(--primary-color)">
          Travelers Fare Details
        </Typography>
        <Typography variant="body2">
          <strong>Cabin:</strong> {cabin}
        </Typography>
        <Typography variant="body2">
          <strong>Class:</strong> {fareClass}
        </Typography>
        <Typography variant="body2">
          <strong>Aircraft Code:</strong> {aircraftCode}
        </Typography>
      </Box>
    </Box>
  );
};
