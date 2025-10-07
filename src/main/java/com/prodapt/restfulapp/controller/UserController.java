package com.prodapt.restfulapp.controller;

import com.prodapt.restfulapp.dto.RegisterRequest;
import com.prodapt.restfulapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.prodapt.restfulapp.service.userService;

import java.util.List;

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

    @PutMapping("/update/{email}")
    public ResponseEntity<User> updateUser(@PathVariable("email") String email , @RequestBody User updateUser){
        User user = service.updateCurrentUser(email,updateUser);
        return  ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = service.getAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id ){
        User user = service.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        service.deleteUser(id);
        return ResponseEntity.ok("the user Deleted Successfully!");
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = service.getCurrentUser(email);
        return ResponseEntity.ok(user);
    }


}
