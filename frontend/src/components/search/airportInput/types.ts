export interface AirportInputProps {
  label: string;
  airport: AirportInfo | string | null;
  onAirportChange: (airport: AirportInfo | null) => void;
  icon: React.ReactNode;
}

export interface AirportInfo {
  name: string;
  iataCode: string;
  address: {
    countryName: string;
  };
}
