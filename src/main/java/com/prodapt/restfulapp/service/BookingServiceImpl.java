package com.prodapt.restfulapp.service;

import com.prodapt.restfulapp.dto.BookingRequest;
import com.prodapt.restfulapp.dto.BookingResponse;
import com.prodapt.restfulapp.entity.Booking;
import com.prodapt.restfulapp.entity.Flight;
import com.prodapt.restfulapp.entity.Seat;
import com.prodapt.restfulapp.entity.User;
import com.prodapt.restfulapp.repository.BookingRepository;
import com.prodapt.restfulapp.repository.FlightRepository;
import com.prodapt.restfulapp.repository.SeatRepository;
import com.prodapt.restfulapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    private BookingRepository repo;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public BookingResponse bookFlights(BookingRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        List<Seat> availableSeats = seatRepository.findByFlightAndIsBookedFalse(flight);

        if (availableSeats.size() < request.getSeatCount()) {

            throw new RuntimeException("Not enough seats available");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setBookingDate(LocalDateTime.now());
        booking.setSeatCount(request.getSeatCount());
        booking.setTotalPrice(flight.getFare() * request.getSeatCount());
        booking.setStatus("BOOKED");
        Booking savedBooking = repo.save(booking);

        List<Seat> seatsToBook = availableSeats.subList(0, request.getSeatCount());
        for (Seat seat : seatsToBook) {
            seat.setBooked(true);
            seat.setBooking(savedBooking);
        }
        seatRepository.saveAll(seatsToBook);



        return new BookingResponse(
                savedBooking.getId(),
                flight.getFlightNumber(),
                flight.getFlightName(),
                flight.getRoute().getOrigin(),
                flight.getRoute().getDestination(),
                savedBooking.getSeatCount(),
                savedBooking.getTotalPrice(),
                savedBooking.getStatus(),
                savedBooking.getBookingDate()
        );
    }


    @Override
    public List<BookingResponse> getBookingDetailsById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Fetch bookings by user
        List<Booking> bookings = repo.findByUser(user);
        if (bookings.isEmpty()) {
            throw new RuntimeException("No bookings found for user ID: " + userId);
        }

        // Map each booking to a BookingResponse DTO
        return bookings.stream()
                .map(booking -> {
                    Flight flight = booking.getFlight();
                    return new BookingResponse(
                            booking.getId(),
                            flight.getFlightNumber(),
                            flight.getFlightName(),
                            flight.getRoute().getOrigin(),
                            flight.getRoute().getDestination(),
                            booking.getSeatCount(),
                            booking.getTotalPrice(),
                            booking.getStatus(),
                            booking.getBookingDate()
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingResponse> getAllBookings() {
        List<Booking> bookings=repo.findAll();
        return bookings.stream()
                .map(booking -> {
                    Flight flight = booking.getFlight();
                    return new BookingResponse(
                            booking.getId(),
                            flight.getFlightNumber(),
                            flight.getFlightName(),
                            flight.getRoute().getOrigin(),
                            flight.getRoute().getDestination(),
                            booking.getSeatCount(),
                            booking.getTotalPrice(),
                            booking.getStatus(),
                            booking.getBookingDate()
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public String cancelBookingById(Long bookingId,String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Booking booking = repo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to cancel this booking");
        }

        if (!"BOOKED".equals(booking.getStatus())) {

            throw new RuntimeException("Only booked tickets can be cancelled");
        }

        booking.setStatus("CANCELLED");
        repo.save(booking);

        List<Seat> seats = seatRepository.findByBookingId(bookingId);
        for (Seat seat : seats) {
            seat.setBooked(false);
            seat.setBooking(null);
        }
        seatRepository.saveAll(seats);

        return "Booking cancelled successfully.";

    }
}
