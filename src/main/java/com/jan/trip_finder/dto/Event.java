package com.jan.trip_finder.dto;

import java.time.LocalDate;

public record Event(
        String name,
        String description,
        LocalDate date
) {
}
