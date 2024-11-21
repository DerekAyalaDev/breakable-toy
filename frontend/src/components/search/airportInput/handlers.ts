import { Dispatch, SetStateAction } from "react";
import { fetchAirports } from "./api";
import { AirportInfo } from "./types";

export const handleInputChange = async (
  value: string,
  setOptions: Dispatch<SetStateAction<AirportInfo[]>>
) => {
  if (value.length < 3) {
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
