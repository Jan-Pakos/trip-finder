package com.jan.trip_finder.dto;

import java.util.List;

public record Transportation(
        List<CarRental> carRentals,
        List<PublicTransportation> publicTransportations
) {
}
