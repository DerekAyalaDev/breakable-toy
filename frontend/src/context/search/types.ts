import { Dayjs } from "dayjs";
import { AirportInfo } from "../../components/search/airportInput/types";

export interface SearchContextState {
  departureAirport: AirportInfo | null;
  arrivalAirport: AirportInfo | null;
  departureDate: Dayjs | null;
  arrivalDate: Dayjs | null;
  currency: string;
  numberOfAdults: number;
  nonStop: boolean;
  pageNumber: number;
  sortByPrice: boolean;
  sortByDuration: boolean;
}

export interface SearchContextProps extends SearchContextState {
  setSearchValues: (key: keyof SearchContextState, value: any) => void;
}
