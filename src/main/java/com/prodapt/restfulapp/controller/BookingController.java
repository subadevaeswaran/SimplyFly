package com.prodapt.restfulapp.controller;

import com.prodapt.restfulapp.dto.BookingRequest;
import com.prodapt.restfulapp.dto.BookingResponse;
import com.prodapt.restfulapp.entity.Booking;
import com.prodapt.restfulapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService service;

    @PostMapping("/add/{email}")
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest request, @PathVariable("email") String email){
        BookingResponse book = service.bookFlights(request, email);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponse>> getBookingsByUserId(@PathVariable Long userId) {
        List<BookingResponse> bookingResponses = service.getBookingDetailsById(userId);
        return ResponseEntity.ok(bookingResponses);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        List<BookingResponse> allBookings = service.getAllBookings();
        return ResponseEntity.ok(allBookings);
    }

    @DeleteMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(
            @PathVariable Long bookingId,
            @RequestParam String userEmail
    ) {
        String message = service.cancelBookingById(bookingId, userEmail);
        return ResponseEntity.ok(message);
    }
}
