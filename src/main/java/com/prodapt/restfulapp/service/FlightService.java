package com.prodapt.restfulapp.service;

import com.prodapt.restfulapp.dto.AddFlightRequest;
import com.prodapt.restfulapp.dto.FlightResponse;
import com.prodapt.restfulapp.dto.FlightSearchRequest;
import com.prodapt.restfulapp.entity.Flight;

import java.util.List;

public interface FlightService {
    void addFlight(AddFlightRequest request, String userEmail);
    List<FlightResponse> getAllFlights();
    List<FlightResponse> searchFlights(FlightSearchRequest request);
    void deleteFlight(Long id);
    FlightResponse getFlightById(Long id);
    void updateFlight(Long id, AddFlightRequest request, String userEmail);
}
