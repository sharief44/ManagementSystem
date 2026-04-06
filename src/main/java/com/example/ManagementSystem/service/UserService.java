package com.example.ManagementSystem.service;



import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ManagementSystem.dto.UserRequest;
import com.example.ManagementSystem.dto.UserResponse;
import com.example.ManagementSystem.entity.User;
import com.example.ManagementSystem.exception.UserNotFoundException;
import com.example.ManagementSystem.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repo;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final PasswordEncoder passwordEncoder;
    
    

    public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder=passwordEncoder;
    }

    public UserResponse createUser(UserRequest request) {

        if (repo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User saved = repo.save(user);
        log.info("Creating user with email: {}", request.getEmail());

        log.warn("Duplicate email attempt: {}", request.getEmail());

        return mapToResponse(saved);
    }

    public List<UserResponse> getAllUsers() {
        return repo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return mapToResponse(user);
    }

    private UserResponse mapToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }
}