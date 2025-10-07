package com.prodapt.restfulapp.repository;

import com.prodapt.restfulapp.entity.Seat;
import com.prodapt.restfulapp.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByFlightAndIsBookedFalse(Flight flight);
    List<Seat> findByBookingId(Long bookingId);
    
    List<Seat> findByFlightId(Long flightId);
   

}
