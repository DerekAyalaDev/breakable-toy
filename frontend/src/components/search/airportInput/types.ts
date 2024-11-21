export interface AirportInputProps {
  label: string;
  airport: string;
  onAirportChange: (airport: string) => void;
  icon: React.ReactNode;
}
