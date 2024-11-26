import { Dispatch, SetStateAction } from "react";
import { fetchAirports } from "../../../apis/fetchAirports";
import { AirportInfo } from "./types";

export const handleInputChange = async (
  value: string,
  setOptions: Dispatch<SetStateAction<AirportInfo[]>>,
  selectedAirport: AirportInfo | null
) => {
  // Stop fetching if an airport is already selected
  if (selectedAirport) {
    setOptions([]);
    return;
  }

  // Ignore if input is too short or too long
  if (value.length < 3 || value.length > 20) {
    setOptions([]);
    return;
  }

  try {
    const airports = await fetchAirports(value);
    setOptions(airports);
  } catch (error) {
    console.error("Error fetching airports:", error);
    setOptions([]);
  }
};
