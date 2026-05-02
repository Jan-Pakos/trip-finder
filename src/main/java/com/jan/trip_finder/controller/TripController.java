package com.jan.trip_finder.controller;

import com.jan.trip_finder.dto.FlightReservationRequest;
import com.jan.trip_finder.dto.FlightReservationResponse;
import com.jan.trip_finder.dto.TripPlan;
import com.jan.trip_finder.service.TripPlanService;
import com.jan.trip_finder.service.TripReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
@Slf4j
public class TripController {

    private final TripPlanService tripPlanService;
    private final TripReservationService tripReservationService;

    @GetMapping("/{airPortCode}")
    public TripPlan getTripPlan(@PathVariable String airPortCode) {
        return this.tripPlanService.getTripPlan(airPortCode);
    }

    @PostMapping
    public FlightReservationResponse reserveTrip(@RequestBody FlightReservationRequest request) {
        return this.tripReservationService.reserve(request);
    }

}
