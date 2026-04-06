package com.example.ManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;

public class UserRequest {
	
	@NotBlank(message="name is required")
	private String name;
	
	@NotBlank(message="invalid email")
	private String email;
	
	@NotBlank(message="invalid email")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
