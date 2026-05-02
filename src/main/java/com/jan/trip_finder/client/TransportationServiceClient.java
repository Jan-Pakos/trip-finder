package com.jan.trip_finder.client;

import com.jan.trip_finder.dto.Event;
import com.jan.trip_finder.dto.Transportation;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class TransportationServiceClient {

    @Qualifier("transportationRestClient")
    private final RestClient restClient;

    @Retry(name = "events", fallbackMethod = "eventsFallback")
    public Transportation getTransportation(String airPortCode) {
        return this.restClient.get()
                .uri("{airPortCode}",airPortCode)
                .retrieve()
                .body(Transportation.class);
    }

    private List<Event> eventsFallback(String airportCode, Throwable t) {
        log.warn("Events service failed after retries for {}: {}", airportCode, t.getMessage());
        return Collections.emptyList();
    }
}
