package com.jan.trip_finder.client;

import com.jan.trip_finder.dto.Weather;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Component
@Slf4j
public class WeatherServiceClient {

    @Qualifier("weatherRestClient")
    private final RestClient restClient;

    @TimeLimiter(name = "weather", fallbackMethod = "weatherFallback")
    public Weather getLocalWeather(String airPortCode) {
        return this.restClient.get()
                .uri("{airPortCode}",airPortCode)
                .retrieve()
                .body(Weather.class);
    }

    private CompletableFuture<Weather> weatherFallback(String airportCode, Throwable t) {
        log.warn("Weather service timed out for {}: {}", airportCode, t.getMessage());
        return CompletableFuture.completedFuture(null);
    }
}
