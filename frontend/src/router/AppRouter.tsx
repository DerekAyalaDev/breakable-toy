import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "../pages/Home";
import { FlightOffers } from "../pages/FlightOffers";
import { Details } from "../pages/Details";

export const AppRouter = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/offers" element={<FlightOffers />} />
        <Route path="/details" element={<Details />} />
      </Routes>
    </Router>
  );
}

export default AppRouter;
