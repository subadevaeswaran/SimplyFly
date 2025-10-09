package com.prodapt.restfulapp.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "flights")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"flightOwner", "route"})
@EqualsAndHashCode(of = "id")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;
    private String flightName;

    @ManyToOne()
    @JoinColumn(name = "route_id")

    private Route route;

    private int totalSeats;
    private double fare;

    private String baggageCheckin; // e.g., "20kg"
    private String baggageCabin;   // e.g., "7kg"

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    @ManyToOne
    @JoinColumn(name = "owner_id") // maps to a USER with role FLIGHT_OWNER or ADMIN
    @JsonIgnore
    private User flightOwner;
}