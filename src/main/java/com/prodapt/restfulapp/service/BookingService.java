package com.prodapt.restfulapp.service;

import com.prodapt.restfulapp.dto.BookingRequest;
import com.prodapt.restfulapp.dto.BookingResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookingService {
    public BookingResponse bookFlights(BookingRequest req,String username);



    public List<BookingResponse> getBookingDetailsById(Long userId);
        public List<BookingResponse> getAllBookings();
    public String cancelBookingById(Long bookingId,String username);
}
