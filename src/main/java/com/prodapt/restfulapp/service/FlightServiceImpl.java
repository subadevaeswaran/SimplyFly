package com.prodapt.restfulapp.service;

import com.prodapt.restfulapp.dto.AddFlightRequest;
import com.prodapt.restfulapp.dto.FlightResponse;
import com.prodapt.restfulapp.dto.FlightSearchRequest;
import com.prodapt.restfulapp.entity.Flight;
import com.prodapt.restfulapp.entity.Route;
import com.prodapt.restfulapp.entity.Seat;
import com.prodapt.restfulapp.entity.User;
import com.prodapt.restfulapp.repository.FlightRepository;
import com.prodapt.restfulapp.repository.RouteRepository;
import com.prodapt.restfulapp.repository.SeatRepository;
import com.prodapt.restfulapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {
@Autowired
    private  FlightRepository flightRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public void addFlight(AddFlightRequest request, String userEmail) {
        Route route = routeRepository.findById(request.getRouteId())
                .orElseThrow(() -> {
                    return new RuntimeException("Route not found");
                });


        User owner = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> {
                    return new RuntimeException("User not found");
                });

        Flight flight = new Flight();
        flight.setFlightNumber(request.getFlightNumber());
        flight.setFlightName(request.getFlightName());
        flight.setRoute(route);
        flight.setTotalSeats(request.getTotalSeats());
        flight.setFare(request.getFare());
        flight.setBaggageCheckin(request.getBaggageCheckin());
        flight.setBaggageCabin(request.getBaggageCabin());
        flight.setDepartureTime(request.getDepartureTime());
        flight.setArrivalTime(request.getArrivalTime());
        flight.setFlightOwner(owner);

        Flight savedFlight = flightRepository.save(flight);

        // ✅ Generate seats for the flight
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= request.getTotalSeats(); i++) {
            Seat seat = new Seat();
            seat.setSeatNumber("S" + i);
            seat.setStatus("AVAILABLE");
            seat.setBooked(false);
            seat.setFlight(savedFlight);
            seats.add(seat);
        }
        seatRepository.saveAll(seats);
    }

    @Override
    public List<FlightResponse> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(flight -> new FlightResponse(
                        flight.getId(),
                        flight.getFlightNumber(),
                        flight.getFlightName(),
                        flight.getRoute().getOrigin(),
                        flight.getRoute().getDestination(),
                        flight.getFare(),
                        flight.getDepartureTime(),
                        flight.getArrivalTime()
                )).collect(Collectors.toList());
    }

    @Override
    public List<FlightResponse> searchFlights(FlightSearchRequest request) {
        LocalDateTime startOfDay = request.getDepartureDate().atStartOfDay();
        LocalDateTime endOfDay = request.getDepartureDate().atTime(23, 59, 59);

        List<Flight> flights = flightRepository
                .findByRoute_OriginAndRoute_DestinationAndDepartureTimeBetween(
                        request.getOrigin(), request.getDestination(),
                        startOfDay, endOfDay);


        return flights.stream()
                .map(flight -> new FlightResponse(
                        flight.getId(),
                        flight.getFlightNumber(),
                        flight.getFlightName(),
                        flight.getRoute().getOrigin(),
                        flight.getRoute().getDestination(),
                        flight.getFare(),
                        flight.getDepartureTime(),
                        flight.getArrivalTime()
                )).collect(Collectors.toList());
    }

    @Override
    public void deleteFlight(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        flightRepository.delete(flight);

    }

    @Override
    public FlightResponse getFlightById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        return new FlightResponse(
                flight.getId(),
                flight.getFlightNumber(),
                flight.getFlightName(),
                flight.getRoute().getOrigin(),
                flight.getRoute().getDestination(),
                flight.getFare(),
                flight.getDepartureTime(),
                flight.getArrivalTime()
        );
    }

    @Override
    public void updateFlight(Long id, AddFlightRequest request, String userEmail) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));


        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getRole().name().equals("ADMIN") &&
                !flight.getFlightOwner().getEmail().equals(userEmail)) {
            throw new RuntimeException("Access denied to update flight");
        }

        Route route = routeRepository.findById(request.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found"));

        flight.setFlightNumber(request.getFlightNumber());
        flight.setFlightName(request.getFlightName());
        flight.setRoute(route);
        flight.setFare(request.getFare());
        flight.setTotalSeats(request.getTotalSeats());
        flight.setBaggageCheckin(request.getBaggageCheckin());
        flight.setBaggageCabin(request.getBaggageCabin());
        flight.setDepartureTime(request.getDepartureTime());
        flight.setArrivalTime(request.getArrivalTime());

        flightRepository.save(flight);
    }
}
