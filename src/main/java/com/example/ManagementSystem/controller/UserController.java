package com.example.ManagementSystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ManagementSystem.dto.UserRequest;
import com.example.ManagementSystem.dto.UserResponse;
import com.example.ManagementSystem.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private final UserService service;
	
	public UserController(UserService service) {
		this.service=service;
		
	}
	
	@RequestMapping
	public UserResponse create(@Valid @RequestBody UserRequest request) {
		return service.createUser(request);
	}
	
	@GetMapping
	public List<UserResponse> getAll(){
		return service.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public UserResponse getById(@PathVariable Long id) {
		return service.getUserById(id);
		}

}
