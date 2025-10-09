package com.prodapt.restfulapp.service;

import com.prodapt.restfulapp.dto.AddRouteRequest;
import com.prodapt.restfulapp.entity.Route;
import com.prodapt.restfulapp.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService{
    @Autowired
    public RouteRepository routeRepository;
    @Override
    public String addRoute(AddRouteRequest routeDTO) {
        Route route = new Route(
                null,
                routeDTO.getOrigin(),
                routeDTO.getDestination(),
                routeDTO.getDistanceInKm(),
                routeDTO.getDuration()
        );
        routeRepository.save(route);
        if(route!=null){
            return "Route added successfully";
        }else{
            return "Route not found";
        }
    }

    @Override
    public List<AddRouteRequest> getAllRoutes() {
        return routeRepository.findAll().stream().map(route ->
                new AddRouteRequest(
                        route.getId(),
                        route.getOrigin(),
                        route.getDestination(),
                        route.getDistanceInKm(),
                        route.getDuration()
                )).collect(Collectors.toList());
    }

    @Override
    public String updateRoute(Long id, AddRouteRequest routeDTO) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));
        route.setOrigin(routeDTO.getOrigin());
        route.setDestination(routeDTO.getDestination());
        route.setDistanceInKm(routeDTO.getDistanceInKm());
        route.setDuration(routeDTO.getDuration());
        routeRepository.save(route);
        return "Route Updated Successfully";
    }

    @Override
    public String deleteRoute(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));
        routeRepository.deleteById(id);
        return "Route Deleted successfully";
    }
}
