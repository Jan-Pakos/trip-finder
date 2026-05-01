package com.jan.trip_finder.dto;

import java.math.BigDecimal;

public record PublicTransportation(
        String type,
        int price
) {
}
