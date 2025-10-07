package com.prodapt.restfulapp.service;



import com.prodapt.restfulapp.dto.RegisterRequest;
import com.prodapt.restfulapp.entity.User;
import com.prodapt.restfulapp.repository.UserRepository;
import com.prodapt.restfulapp.service.userService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements userService {
    private final UserRepository userRepository;

    @Override
    public String registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setContactNumber(request.getContactNumber());
        user.setAddress(request.getAddress());
        user.setRole(request.getRole());


        userRepository.save(user);
        return "User registered successfully";
    }

    // 🟢 Fetch all users
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    // 🟢 Fetch user by ID
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    // 🟢 Delete user by ID
    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    // 🟢 Get current user by email
    @Override
    public User getCurrentUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    // 🟢 Update current user by email
    @Override
    public User updateCurrentUser(String email, User updatedUser) {
        User existingUser = getCurrentUser(email);

        existingUser.setName(updatedUser.getName());
        existingUser.setAddress(updatedUser.getAddress());
        existingUser.setContactNumber(updatedUser.getContactNumber());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRole(updatedUser.getRole());

        return userRepository.save(existingUser);
    }
}
