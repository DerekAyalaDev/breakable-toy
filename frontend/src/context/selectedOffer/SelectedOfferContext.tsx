import React, { createContext, useState, useContext } from "react";
import { FlightOffer } from "../flightOffers/types";

interface SelectedOfferContextProps {
  selectedOffer: FlightOffer | null;
  setSelectedOffer: (offer: FlightOffer | null) => void;
}

const SelectedOfferContext = createContext<SelectedOfferContextProps | null>(null);

export const SelectedOfferProvider = ({ children }: { children: React.ReactNode }) => {
  const [selectedOffer, setSelectedOffer] = useState<FlightOffer | null>(null);

  return (
    <SelectedOfferContext.Provider value={{ selectedOffer, setSelectedOffer }}>
      {children}
    </SelectedOfferContext.Provider>
  );
};

export const useSelectedOfferContext = () => {
  const context = useContext(SelectedOfferContext);
  if (!context) {
    throw new Error(
      "useSelectedOfferContext must be used within a SelectedOfferProvider"
    );
  }
  return context;
};
