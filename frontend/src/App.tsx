import { FlightProvider } from "./context/flightOffers/FlightsContext";
import { SearchProvider } from "./context/search/SearchContext";
import AppRouter from "./router/AppRouter";

export const App = () => {
  return (
    <SearchProvider>
      <FlightProvider>
        <AppRouter />
      </FlightProvider>
    </SearchProvider>
  );
};

export default App;
