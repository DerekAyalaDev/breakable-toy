import axios from "axios";
import { AirportInfo } from "./types";

export const fetchAirports = async (keyword: string): Promise<AirportInfo[]> => {
  if (!keyword.trim()) return [];

  try {
    const response = await axios.get("http://localhost:9090/api/airports/search", {
      params: { keyword },
    });
    return response.data;
  } catch (error) {
    console.error("Failed to fetch airports:", error);
    return [];
  }
};
