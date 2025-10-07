package com.prodapt.restfulapp.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    private Long flightId;
    private int seatCount;
}
