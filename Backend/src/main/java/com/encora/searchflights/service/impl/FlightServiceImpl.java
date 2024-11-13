package com.encora.searchflights.service.impl;

import com.encora.searchflights.config.WebClientConfig;
import com.encora.searchflights.model.dto.FlightOfferResponseDTO;
import com.encora.searchflights.model.dto.FlightSearchRequestDTO;
import com.encora.searchflights.model.flights.FlightOffer;
import com.encora.searchflights.service.FlightService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final WebClient webClient;
    private final WebClientConfig webClientConfig;

    @Override
    public FlightOfferResponseDTO searchFlights(FlightSearchRequestDTO request) {
        List<FlightOffer> flightOffers = fetchFlightOffers(request);

        // Sort the flight offers based on sort parameters
        List<FlightOffer> sortedOffers = sortFlightOffers(flightOffers, request.getSortByPrice(), request.getSortByDuration());

        // Paginate the sorted list and return response DTO
        return createPaginatedResponse(sortedOffers, request.getPageNumber(), 10);
    }

    /**
     * Fetches up to 100 flight offers from the Amadeus API based on the search criteria in the request.
     *
     * @param request the search criteria including origin, destination, dates, etc.
     * @return a list of flight offers.
     */
    private List<FlightOffer> fetchFlightOffers(FlightSearchRequestDTO request) {
        String token = webClientConfig.getAccessToken().block();
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/shopping/flight-offers")
                        .queryParam("max", 100)
                        .queryParam("originLocationCode", request.getDepartureAirportCode())
                        .queryParam("destinationLocationCode", request.getArrivalAirportCode())
                        .queryParam("departureDate", request.getDepartureDate().toString())
                        .queryParam("returnDate", request.getReturnDate() != null ? request.getReturnDate().toString() : null)
                        .queryParam("adults", request.getNumberOfAdults())
                        .queryParam("currencyCode", request.getCurrency())
                        .queryParam("nonStop", request.isNonStop())
                        .build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToFlux(FlightOffer.class)
                .collectList()
                .block();
    }

    /**
     * Sorts a list of flight offers based on price and duration as specified in the parameters.
     *
     * @param flightOffers the list of flight offers to be sorted.
     * @param sortByPrice sorting order for price: "ASC" for ascending, "DESC" for descending, or empty for no sorting.
     * @param sortByDuration sorting order for duration: "ASC" for ascending, "DESC" for descending, or empty for no sorting.
     * @return a sorted list of flight offers.
     */
    private List<FlightOffer> sortFlightOffers(List<FlightOffer> flightOffers, String sortByPrice, String sortByDuration) {
        Comparator<FlightOffer> comparator = Comparator.comparing(offer -> 0); // Start with a no-op comparator

        if ("ASC".equalsIgnoreCase(sortByPrice)) {
            comparator = comparator.thenComparing(offer -> offer.getPriceBreakdown().getTotal());
        } else if ("DESC".equalsIgnoreCase(sortByPrice)) {
            comparator = comparator.thenComparing((offer1, offer2) -> Double.compare(offer2.getPriceBreakdown().getTotal(), offer1.getPriceBreakdown().getTotal()));
        }

        if ("ASC".equalsIgnoreCase(sortByDuration)) {
            comparator = comparator.thenComparing(this::getTotalDuration);
        } else if ("DESC".equalsIgnoreCase(sortByDuration)) {
            comparator = comparator.thenComparing((offer1, offer2) -> getTotalDuration(offer2).compareTo(getTotalDuration(offer1)));
        }

        return flightOffers.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    /**
     * Creates a paginated response DTO from a sorted list of flight offers.
     *
     * @param sortedOffers the sorted list of flight offers.
     * @param pageNumber the current page number requested.
     * @param pageSize the number of offers per page.
     * @return a response DTO with paginated flight offers and the total page count.
     */
    private FlightOfferResponseDTO createPaginatedResponse(List<FlightOffer> sortedOffers, int pageNumber, int pageSize) {
        int totalPages = (int) Math.ceil((double) sortedOffers.size() / pageSize);
        int start = (pageNumber - 1) * pageSize;
        int end = Math.min(start + pageSize, sortedOffers.size());
        List<FlightOffer> paginatedOffers = sortedOffers.subList(start, end);

        FlightOfferResponseDTO response = new FlightOfferResponseDTO();
        response.setOffers(paginatedOffers);
        response.setTotalPages(totalPages);
        return response;
    }

    /**
     * Calculates the total duration of a flight offer by summing up the duration of each itinerary.
     *
     * @param offer the flight offer to calculate the total duration for.
     * @return the total duration as a Duration object.
     */
    private Duration getTotalDuration(FlightOffer offer) {
        return offer.getItineraries().stream()
                .map(itinerary -> Duration.parse(itinerary.getTotalDuration()))
                .reduce(Duration.ZERO, Duration::plus);
    }
}
