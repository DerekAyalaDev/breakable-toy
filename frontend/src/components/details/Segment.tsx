import React from "react";
import { Box, Typography } from "@mui/material";

interface SegmentProps {
  segmentId: string;
  segmentNumber: number;
  type: "outbound" | "return"; // 'outbound' for departure, 'return' for the return flight
  aircraftCode: string;
  cabin: string;
  fareClass: string;
}

export const Segment: React.FC<SegmentProps> = ({
  segmentId,
  segmentNumber,
  type,
  aircraftCode,
  cabin,
  fareClass,
}) => {
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
        <Typography variant="h6" fontWeight={600}>
          Segment {segmentNumber} ({type === "outbound" ? "Outbound" : "Return"})
        </Typography>
        {/* Placeholder for future details */}
        <Typography variant="body2" color="text.secondary">
          More information about this segment will go here.
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
