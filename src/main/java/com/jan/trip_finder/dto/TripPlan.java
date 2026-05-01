package com.jan.trip_finder.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record TripPlan(
        String airPortCode,
        List<Accommodation> accommodations,
        Weather weather,
        List<Event> events,
        LocalRecommendations localRecommendations,
        Transportation transportation
) {
}
