package com.prodapt.restfulapp.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightSearchRequest {
    private String origin;
    private String destination;
    private LocalDate departureDate;
}
