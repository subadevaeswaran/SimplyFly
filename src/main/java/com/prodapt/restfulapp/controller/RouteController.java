package com.prodapt.restfulapp.controller;

import com.prodapt.restfulapp.dto.AddRouteRequest;
import com.prodapt.restfulapp.entity.Route;
import com.prodapt.restfulapp.service.RouteService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RouteController {
    @Autowired
    private RouteService routeService;

    @PostMapping("/add/route")
    public ResponseEntity<String> addRoute(@RequestBody AddRouteRequest routeDTO){
        routeService.addRoute(routeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Route Created Successfully");
    }
    @GetMapping
    public ResponseEntity<List<AddRouteRequest>> getAllRoutes(){
        List<AddRouteRequest> routes = routeService.getAllRoutes();
        return ResponseEntity.ok(routes);
    }
    @PutMapping("/update/route/{id}")
    public ResponseEntity<String> updateRoute(@PathVariable Long id,
                                              @RequestBody AddRouteRequest route){
        routeService.updateRoute(id, route);
        return ResponseEntity.status(HttpStatus.CREATED).body("Route updated successfully");
    }
    @DeleteMapping("delete/route/{id}")
    public ResponseEntity<String> deleteRoute(@PathVariable Long id){
        routeService.deleteRoute(id);
        return ResponseEntity.status(200).body("Route deleted successfully");
    }
}
