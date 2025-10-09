package com.prodapt.restfulapp.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddRouteRequest {
    private Long id;
    private String origin;
    private String destination;
    private String distanceInKm;
    private String duration; // e.g., "2h 45m"
}