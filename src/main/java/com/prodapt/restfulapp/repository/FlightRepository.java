package com.prodapt.restfulapp.repository;

import com.prodapt.restfulapp.dto.FlightResponse;
import com.prodapt.restfulapp.entity.Flight;
import  com.prodapt.restfulapp.entity.Route;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByRouteAndDepartureTimeBetween(Route route, LocalDateTime start, LocalDateTime end);
    List<Flight> findByRoute_OriginAndRoute_DestinationAndDepartureTimeBetween(
    	    String origin, String destination, LocalDateTime start, LocalDateTime end);

    Flight findByFlightNumber(String flightNumber);
}

