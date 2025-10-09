package com.prodapt.restfulapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "routes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origin;
    private String destination;
    
    @Column(name = "distance")
    private String distanceInKm;
    
    private String duration; // Format: "2h 30m"
}