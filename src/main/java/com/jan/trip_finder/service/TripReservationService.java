package com.jan.trip_finder.service;

import com.jan.trip_finder.client.FlightReservationServiceClient;
import com.jan.trip_finder.client.FlightSearchServiceClient;
import com.jan.trip_finder.dto.Flight;
import com.jan.trip_finder.dto.FlightReservationRequest;
import com.jan.trip_finder.dto.FlightReservationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class TripReservationService {

    private final FlightSearchServiceClient flightSearchServiceClient;
    private final FlightReservationServiceClient flightReservationServiceClient;

    public FlightReservationResponse reserve(FlightReservationRequest flightReservationRequest) {
        List<Flight> flights = flightSearchServiceClient.getFlights(flightReservationRequest.departure(), flightReservationRequest.arrival());
        var bestDeal = flights.stream().min(Comparator.comparingInt(Flight::price));
        var flight = bestDeal.orElseThrow(() -> new IllegalStateException("No flights found"));

        var reservationRequest = FlightReservationRequest.builder()
                .departure(flightReservationRequest.departure())
                .arrival(flightReservationRequest.arrival())
                .flightNumber(flight.flightNumber())
                .tripDate(flightReservationRequest.tripDate())
                .build();

        return flightReservationServiceClient.reserve(reservationRequest);
    }
}
