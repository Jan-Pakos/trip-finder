package com.jan.trip_finder.dto;

import java.time.LocalDate;

public record Flight(
        String flightNumber,
        String airline,
        int price,
        LocalDate date,
        int flightDurationInMinutes
) {
}
