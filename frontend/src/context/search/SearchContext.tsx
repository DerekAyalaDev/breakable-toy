import React, { createContext, useContext, useState } from "react";
import { SearchContextProps, SearchContextState } from "./types";

const SearchContext = createContext<SearchContextProps | undefined>(undefined);

export const SearchProvider = ({ children }: { children: React.ReactNode }) => {
  const [state, setState] = useState<SearchContextState>({
    departureAirport: null,
    arrivalAirport: null,
    departureDate: null,
    arrivalDate: null,
    currency: "",
    numberOfAdults: 1,
    nonStop: false,
    pageNumber: 1,
    sortByPrice: false,
    sortByDuration: false,
  });

  const setSearchValues = (key: keyof SearchContextState, value: any) => {
    setState((prev) => ({ ...prev, [key]: value }));
  };

  return (
    <SearchContext.Provider value={{ ...state, setSearchValues }}>
      {children}
    </SearchContext.Provider>
  );
};

export const useSearchContext = () => {
  const context = useContext(SearchContext);
  if (!context) {
    throw new Error("useSearchContext must be used within a SearchProvider");
  }
  return context;
};
