package com.prodapt.restfulapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;
import com.prodapt.restfulapp.enums.Role;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"password"}) // prevent recursive logging
@EqualsAndHashCode(of = "id")     // safer comparison
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    private String password;

    private String contactNumber;

    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}


