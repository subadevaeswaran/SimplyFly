package com.prodapt.restfulapp.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddFlightRequest {
    private String flightNumber;
    private String flightName;
    private Long routeId; // Foreign key to Route
    private int totalSeats;
    private double fare;
    private String baggageCheckin;
    private String baggageCabin;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
}
