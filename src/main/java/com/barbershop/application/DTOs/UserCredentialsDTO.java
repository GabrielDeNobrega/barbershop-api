package com.barbershop.application.DTOs;

public class UserCredentialsDTO {
	public Long id;
	public String email;
	public String username;
	public String password;
	public String role;
	
	UserCredentialsDTO() { }
	
	public UserCredentialsDTO(Long id, String email, String username, String role) {
		this.id = id;
		this.email = email;
		this.username = username;
		this.role = role;
	}
}
