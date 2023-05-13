package com.barbershop.application.DTOs;

public class UserCredentialsDTO {
	public Long id;
	public String email;
	public String password;
	public String token;
	
	UserCredentialsDTO() { }
	
	public UserCredentialsDTO(Long id, String email, String password, String token) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.token = token;
	}
	
	public UserCredentialsDTO(Long id, String email, String token) {
		this.id = id;
		this.email = email;
		this.token = token;
	}
}
