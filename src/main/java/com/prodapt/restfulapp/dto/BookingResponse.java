package com.prodapt.restfulapp.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private Long bookingId;
    private String flightNumber;
    private String flightName;
    private String origin;
    private String destination;
    private int seatCount;
    private double totalPrice;
    private String status;
    private LocalDateTime bookingDate;
}
