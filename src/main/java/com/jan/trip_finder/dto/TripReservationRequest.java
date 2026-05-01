package com.jan.trip_finder.dto;

import java.time.LocalDate;

public record TripReservationRequest(
        String departure,
        String arrival,
        LocalDate date
) {
}
