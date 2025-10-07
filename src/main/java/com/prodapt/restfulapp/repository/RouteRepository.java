package com.prodapt.restfulapp.repository;


import  com.prodapt.restfulapp.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    boolean existsByOriginAndDestination(String origin, String destination);
}