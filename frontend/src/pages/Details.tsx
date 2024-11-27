import { Box, Container } from "@mui/material";
import { Navbar } from "../components/navbar/AppBar";
import { BackButton } from "../components/offers/BackButton";
import { useSelectedOfferContext } from "../context/selectedOffer/SelectedOfferContext";
import { PriceDetails } from "../components/details/PriceDetails";
import { Segment } from "../components/details/Segment";

export const Details = () => {
  const { selectedOffer } = useSelectedOfferContext();

  const base = selectedOffer?.price.base
    ? parseFloat(selectedOffer.price.base)
    : 0;
  const total = selectedOffer?.price.total
    ? parseFloat(selectedOffer.price.total)
    : 0;
  const perTraveler = selectedOffer?.travelerPricings[0]?.price.total
    ? parseFloat(selectedOffer.travelerPricings[0].price.total)
    : 0;

  // Calcular fees/impuestos
  const fees = total - base;

  return (
    <>
      <Navbar />
      <Container
        maxWidth="md"
        sx={{
          marginTop: "2rem",
          marginBottom: "4rem",
          display: "flex",
          flexDirection: "column",
          justifyContent: "flex-start",
        }}
      >
        <Box>
          <BackButton to="/offers" label="Return to Offers" />
        </Box>
        <Box
          sx={{
            display: "flex",
            flexDirection: { xs: "column", md: "row" },
            gap: "1rem",
          }}
        >
          <Box
            sx={{
              flex: 2,
              border: "1px solid var(--secondary-color)",
              borderRadius: "8px",
              padding: "1rem",
              backgroundColor: "#fff",
            }}
          >
            {selectedOffer?.itineraries.map((itinerary, itineraryIndex) => (
              <Box key={itineraryIndex}>
                {itinerary.segments.map((segment, segmentIndex) => {
                  const type = itineraryIndex === 0 ? "outbound" : "return";
                  const fareDetails =
                    selectedOffer.travelerPricings[0]?.fareDetailsBySegment.find(
                      (fare) => fare.segmentId === segment.id
                    );

                  return (
                    <Segment
                      key={segment.id}
                      segmentId={segment.id}
                      segmentNumber={segmentIndex + 1}
                      type={
                        selectedOffer.itineraries.length > 1 ? type : "outbound"
                      }
                      departureAirport={segment.departure.iataCode}
                      arrivalAirport={segment.arrival.iataCode}
                      departureTime={new Date(segment.departure.at).toLocaleString()}
                      arrivalTime={new Date(segment.arrival.at).toLocaleString()}
                      duration={segment.duration.replace("PT", "").toLowerCase()} // Format ISO duration
                      airlineCode={segment.carrierCode}
                      flightNumber={segment.number}
                      aircraftCode={segment.aircraft.code}
                      cabin={fareDetails?.cabin || "N/A"}
                      fareClass={fareDetails?.class || "N/A"}
                    />
                  );
                })}
              </Box>
            ))}
          </Box>

          <PriceDetails
            base={base.toFixed(2)}
            fees={fees.toFixed(2)}
            total={total.toFixed(2)}
            currency={selectedOffer?.price.currency}
            perTraveler={perTraveler.toFixed(2)}
          />
        </Box>
      </Container>
    </>
  );
};
