package com.jan.trip_finder.client;

import com.jan.trip_finder.dto.LocalRecommendations;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
@Component
public class LocalRecommendationServiceClient {

    @Qualifier("localRecommendationRestClient")
    private final RestClient restClient;

    public LocalRecommendations getLocalRecommendations(String airPortCode) {
        return this.restClient.get()
                .uri("{airPortCode}",airPortCode)
                .retrieve()
                .body(LocalRecommendations.class);
    }
}
