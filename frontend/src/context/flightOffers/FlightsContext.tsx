import React, { createContext, useState, useContext } from 'react';
import { FlightOfferResponse } from './types';

const FlightContext = createContext<{
  flightData: FlightOfferResponse | null;
  setFlightData: (data: FlightOfferResponse) => void;
} | null>(null);

export const FlightProvider =  ({ children }: { children: React.ReactNode }) => {
  const [flightData, setFlightData] = useState<FlightOfferResponse | null>(null);

  return (
    <FlightContext.Provider value={{ flightData, setFlightData }}>
      {children}
    </FlightContext.Provider>
  );
};

export const useFlightContext = () => {
  const context = useContext(FlightContext);
  if (!context) {
    throw new Error("useFlightContext must be used within FlightProvider");
  }
  return context;
};
