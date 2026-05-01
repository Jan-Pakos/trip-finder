package com.jan.trip_finder.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {

    private final ServiceProperties props;

    @Bean
    public RestClient eventRestClient() {
        return RestClient.builder()
                .baseUrl(props.getEventUrl())
                .build();
    }

    @Bean
    public RestClient accommodationRestClient() {
        return RestClient.builder()
                .baseUrl(props.getAccommodationUrl())
                .build();
    }

    @Bean
    public RestClient weatherRestClient() {
        return RestClient.builder()
                .baseUrl(props.getWeatherUrl())
                .build();
    }

    @Bean
    public RestClient transportationRestClient() {
        return RestClient.builder()
                .baseUrl(props.getTransportationUrl())
                .build();
    }

    @Bean
    public RestClient localRecommendationRestClient() {
        return RestClient.builder()
                .baseUrl(props.getLocalRecommendationUrl())
                .build();
    }

    @Bean
    public RestClient flightSearchRestClient() {
        return RestClient.builder()
                .baseUrl(props.getFlightSearchUrl())
                .build();
    }

    @Bean
    public RestClient flightReservationRestClient() {
        return RestClient.builder()
                .baseUrl(props.getFlightReservationUrl())
                .build();
    }
}
