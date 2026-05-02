package com.jan.trip_finder.client;

import com.jan.trip_finder.dto.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
@Component
public class EventServiceClient {

    @Qualifier("eventRestClient")
    private final RestClient restClient;

    public List<Event> getEvents(String airPortCode) {
        return this.restClient.get()
                .uri("{airPortCode}",airPortCode)
                .retrieve().body(new ParameterizedTypeReference<>() {

                });
    }
}
