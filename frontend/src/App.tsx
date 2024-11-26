import { FlightProvider } from "./context/flightOffers/FlightsContext";
import { SearchProvider } from "./context/search/SearchContext";
import { SelectedOfferProvider } from "./context/selectedOffer/SelectedOfferContext";
import AppRouter from "./router/AppRouter";

export const App = () => {
  return (
    <SearchProvider>
      <FlightProvider>
        <SelectedOfferProvider>
          <AppRouter />
        </SelectedOfferProvider>
      </FlightProvider>
    </SearchProvider>
  );
};

export default App;
