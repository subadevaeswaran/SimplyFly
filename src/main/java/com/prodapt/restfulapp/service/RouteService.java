package com.prodapt.restfulapp.service;

import com.prodapt.restfulapp.dto.AddRouteRequest;
import com.prodapt.restfulapp.entity.Route;

import java.util.List;

public interface RouteService {
    public String addRoute(AddRouteRequest routeDTO);
    public List<AddRouteRequest> getAllRoutes();
    public String updateRoute(Long id, AddRouteRequest routeDTO);
    public String deleteRoute(Long id);
}
