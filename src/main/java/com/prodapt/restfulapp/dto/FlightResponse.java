package com.prodapt.restfulapp.dto;

import com.prodapt.restfulapp.entity.Route;
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
    private String distanceInKm;
    private String duration;
    private double fare;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

}
