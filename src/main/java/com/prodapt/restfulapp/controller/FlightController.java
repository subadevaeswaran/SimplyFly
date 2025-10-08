package com.prodapt.restfulapp.controller;

import com.prodapt.restfulapp.dto.AddFlightRequest;
import com.prodapt.restfulapp.dto.FlightResponse;
import com.prodapt.restfulapp.entity.Flight;
import com.prodapt.restfulapp.repository.FlightRepository;
import com.prodapt.restfulapp.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
@CrossOrigin(origins = "http://localhost:3000")

public class FlightController {
    // Define flight-related endpoints here
    @Autowired
    FlightService flightService;

    @Autowired
    private FlightRepository flightRepository;

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


    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam String origin,
            @RequestParam String destination
    ) {
        List<Flight> flights = flightRepository.findByRoute_OriginAndRoute_Destination(origin, destination);
        return ResponseEntity.ok(flights);
    }

}
