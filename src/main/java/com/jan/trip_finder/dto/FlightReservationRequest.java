package com.jan.trip_finder.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record FlightReservationRequest(
        String departure,
        String arrival,
        String flightNumber,
        LocalDate tripDate
) {
}
