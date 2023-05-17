package com.barbershop.application.DTOs;

public class AuthCredentialsDTO {
	public String token;
	public UserCredentialsDTO user;
	
	public AuthCredentialsDTO() {}
	
	public AuthCredentialsDTO(String token, UserCredentialsDTO user) {
		super();
		this.token = token;
		this.user = user;
	}
}
