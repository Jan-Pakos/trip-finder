package com.jan.trip_finder.client;

import com.jan.trip_finder.dto.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
@Component
public class FlightSearchServiceClient {

    @Qualifier("flightSearchRestClient")
    private final RestClient restClient;

    public List<Flight> getFlights(String departure, String arrival) {
        return this.restClient.get()
                .uri("/{departure}/{arrival}", departure, arrival)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}
