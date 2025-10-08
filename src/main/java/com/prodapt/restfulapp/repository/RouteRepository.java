package com.prodapt.restfulapp.repository;


import  com.prodapt.restfulapp.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    boolean existsByOriginAndDestination(String origin, String destination);


    @Query("SELECT DISTINCT r.origin FROM Route r WHERE LOWER(r.origin) LIKE LOWER(CONCAT(:query, '%'))")
    List<String> findDistinctOriginCities(@Param("query") String query);


    @Query("SELECT DISTINCT r.destination FROM Route r WHERE LOWER(r.destination) LIKE LOWER(CONCAT(:query, '%'))")
    List<String> findDistinctDestinationCities(@Param("query") String query);
}