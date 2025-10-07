package com.prodapt.restfulapp.controller;

import com.prodapt.restfulapp.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.prodapt.restfulapp.service.userService;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private userService service;


    @PostMapping("/register")
    public ResponseEntity<String> RegisterUser(@RequestBody RegisterRequest request){
        String res = service.registerUser(request);
        return ResponseEntity.ok(res);

    }



}
