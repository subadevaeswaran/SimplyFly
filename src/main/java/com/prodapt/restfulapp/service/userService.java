package com.prodapt.restfulapp.service;

import com.prodapt.restfulapp.dto.RegisterRequest;
import com.prodapt.restfulapp.entity.User;

import java.util.List;

public interface userService {
    String registerUser(RegisterRequest request);
    List<User> getAllUser();

    User getUserById(Long id);

    void deleteUser(Long id);

    User getCurrentUser(String email);
    User updateCurrentUser(String email, User updatedUser);
}
