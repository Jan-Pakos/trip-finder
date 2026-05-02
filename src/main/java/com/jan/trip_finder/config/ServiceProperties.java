package com.jan.trip_finder.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "services")
@Component
@Getter
@Setter
public class ServiceProperties {

    private String accommodationUrl;
    private String eventUrl;
    private String localRecommendationUrl;
    private String transportationUrl;
    private String weatherUrl;
    private String flightSearchUrl;
    private String flightReservationUrl;
}
