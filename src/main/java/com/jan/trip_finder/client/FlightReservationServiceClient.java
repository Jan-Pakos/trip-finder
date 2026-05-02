package com.jan.trip_finder.client;

import com.jan.trip_finder.dto.FlightReservationRequest;
import com.jan.trip_finder.dto.FlightReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
@Component
public class FlightReservationServiceClient {

    @Qualifier("flightReservationRestClient")
    private final RestClient restClient;

    public FlightReservationResponse reserve(FlightReservationRequest flightReservationRequest) {
        return this.restClient.post()
                .body(flightReservationRequest)
                .retrieve()
                .body(FlightReservationResponse.class);

    }
}
