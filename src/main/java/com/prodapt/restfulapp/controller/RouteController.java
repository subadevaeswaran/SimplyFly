package com.prodapt.restfulapp.controller;

import com.prodapt.restfulapp.dto.AddRouteRequest;
import com.prodapt.restfulapp.entity.Route;
import com.prodapt.restfulapp.repository.RouteRepository;
import com.prodapt.restfulapp.service.RouteService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/routes")
@CrossOrigin(origins = "http://localhost:3000")
public class RouteController {
    @Autowired
    private RouteService routeService;
    @Autowired
    private RouteRepository repo;

    @PostMapping("/add/route")
    public ResponseEntity<String> addRoute(@RequestBody AddRouteRequest routeDTO) {
        routeService.addRoute(routeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Route Created Successfully");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AddRouteRequest>> getAllRoutes() {
        List<AddRouteRequest> routes = routeService.getAllRoutes();
        return ResponseEntity.ok(routes);
    }

    @PutMapping("/update/route/{id}")
    public ResponseEntity<String> updateRoute(@PathVariable Long id,
                                              @RequestBody AddRouteRequest route) {
        routeService.updateRoute(id, route);
        return ResponseEntity.status(HttpStatus.CREATED).body("Route updated successfully");
    }

    @DeleteMapping("delete/route/{id}")
    public ResponseEntity<String> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.status(200).body("Route deleted successfully");
    }

    @GetMapping("/routes/cities")
    public ResponseEntity<List<String>> getCities(@RequestParam String query) {
        List<String> origins = repo.findDistinctOriginCities(query);
        List<String> destinations = repo.findDistinctDestinationCities(query);

        // Merge and remove duplicates
        Set<String> cities = new HashSet<>();
        cities.addAll(origins);
        cities.addAll(destinations);

        return ResponseEntity.ok(new ArrayList<>(cities));
    }
}
