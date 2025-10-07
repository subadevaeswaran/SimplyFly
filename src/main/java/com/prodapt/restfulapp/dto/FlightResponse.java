package com.prodapt.restfulapp.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightResponse {
    private Long id;
    private String flightNumber;
    private String flightName;
    private String origin;
    private String destination;
    private double fare;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
}
