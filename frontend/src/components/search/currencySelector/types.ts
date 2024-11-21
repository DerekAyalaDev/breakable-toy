export interface CurrencySelectorProps {
  label: string;
  currency: string;
  onCurrencyChange: (currency: string) => void;
}
