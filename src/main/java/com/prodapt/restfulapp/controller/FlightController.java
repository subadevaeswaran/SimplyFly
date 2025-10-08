package com.prodapt.restfulapp.controller;

import com.prodapt.restfulapp.dto.AddFlightRequest;
import com.prodapt.restfulapp.dto.FlightResponse;
import com.prodapt.restfulapp.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    // Define flight-related endpoints here
    @Autowired
    FlightService flightService;

    //get all flights
    @GetMapping("/all")
    public ResponseEntity<List<FlightResponse>> getAllFlights() {
        List<FlightResponse> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFlight(@RequestBody AddFlightRequest request,@RequestParam String userEmail) {
        flightService.addFlight(request,userEmail);
        return ResponseEntity.ok("Flight added successfully");
    }
}
