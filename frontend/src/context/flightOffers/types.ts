export interface FlightOfferResponse {
  offers: FlightOffer[];
  totalPages: number;
}

export interface FlightOffer {
  id: string;
  oneWay: boolean;
  itineraries: Itinerary[];
  price: Price;
  pricingOptions: PricingOptions;
  travelerPricings: TravelerPricing;
}

export interface Itinerary {
  duration: string;
  segments: Segment[];
}

export interface Segment {
  departure: FlightEndPoint;
  arrival: FlightEndPoint;
  carrierCode: string;
  number: string;
  aircraft: Aircraft;
  operating: OperatingFlight;
  duration: string;
  id: string;
  numberOfStops: number;
}

export interface Aircraft {
  code: string;
}

export interface OperatingFlight {
  carrierCode: String;
}

export interface FlightEndPoint {
  iataCode: string;
  terminal: string;
  at: string;
}

export interface Price {
  currency: string;
  total: string;
  base: string;
  fees: Fee[];
  grandTotal: string;
}

export interface Fee {
  amount: string;
  type: string;
}

export interface PricingOptions {
  fareType: string[];
  includedCheckedBagsOnly: boolean;
}

export interface TravelerPricing {
  travelerId: string;
  fareOption: string;
  travelerType: string;
  price: Price;
  fareDetailsBySegment: FareDetailsBySegment[];
}

export interface FareDetailsBySegment {
  segmentId: string;
  cabin: string;
  fareBasis: string;
  class: string;
  includedCheckedBags: IncludedCheckedBags;
}

export interface IncludedCheckedBags {
  weight: number;
  weightUnit: string;
}
