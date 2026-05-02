package com.jan.trip_finder.service;

import com.jan.trip_finder.client.*;
import com.jan.trip_finder.dto.TripPlan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
@Slf4j
public class TripPlanService {

    private final EventServiceClient eventServiceClient;
    private final WeatherServiceClient weatherServiceClient;
    private final AccommodationServiceClient accommodationServiceClient;
    private final LocalRecommendationServiceClient localRecommendationServiceClient;
    private final TransportationServiceClient transportationServiceClient;

    @Qualifier("virtualThreadExecutor")
    private final ExecutorService executor;

    public TripPlan getTripPlan(String airportCode) {
        var eventsFuture = executor.submit(() -> eventServiceClient.getEvents(airportCode));
        var weatherFuture = executor.submit(() -> weatherServiceClient.getLocalWeather(airportCode));
        var accommodationsFuture = executor.submit(() -> accommodationServiceClient.getAccommodations(airportCode));
        var transportationFuture = executor.submit(() -> transportationServiceClient.getTransportation(airportCode));
        var recommendationsFuture = executor.submit(() -> localRecommendationServiceClient.getLocalRecommendations(airportCode));

        TripPlan tripPlan = TripPlan.builder()
                .events(getOrElse(eventsFuture, Collections.emptyList()))
                .weather(getOrElse(weatherFuture, null))
                .accommodations(getOrElse(accommodationsFuture, Collections.emptyList()))
                .airPortCode(airportCode)
                .localRecommendations(getOrElse(recommendationsFuture, null))
                .transportation(getOrElse(transportationFuture, null))
                .build();

        return tripPlan;
    }

    private <T> T getOrElse(Future<T> future,T defaultValue) {
        try {
            return future.get();
        } catch (Exception e) {
            log.error("Error while fetching data: {}", e.getMessage());
        }
        return defaultValue;
}
}
