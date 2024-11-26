import React, { createContext, useContext, useEffect, useState } from "react";
import { SearchContextProps, SearchContextState } from "./types";
import dayjs from "dayjs";

const SearchContext = createContext<SearchContextProps | null>(null);

const LOCAL_STORAGE_KEY = 'searchContext';

export const SearchProvider = ({ children }: { children: React.ReactNode }) => {
  const loadInitialState = (): SearchContextState => {
    const savedState = localStorage.getItem(LOCAL_STORAGE_KEY);
    const parsedState = savedState ? JSON.parse(savedState) : null;

    return parsedState
      ? {
          ...parsedState,
          departureDate: parsedState.departureDate
            ? dayjs(parsedState.departureDate)
            : null,
          arrivalDate: parsedState.arrivalDate
            ? dayjs(parsedState.arrivalDate)
            : null,
        }
      : {
          departureAirport: null,
          arrivalAirport: null,
          departureDate: null,
          arrivalDate: null,
          currency: '',
          numberOfAdults: 1,
          nonStop: false,
          pageNumber: 1,
          sortByPrice: false,
          sortByDuration: false,
        };
  };

  const [state, setState] = useState<SearchContextState>(loadInitialState);

  useEffect(() => {
    localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify(state));
  }, [state]);

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
    throw new Error('useSearchContext must be used within a SearchProvider');
  }
  return context;
};
