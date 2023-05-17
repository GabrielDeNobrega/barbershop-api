package com.barbershop.application.mappers;

import com.barbershop.application.DTOs.AuthCredentialsDTO;
import com.barbershop.application.DTOs.UserCredentialsDTO;
import com.barbershop.application.entities.User;

public class AuthMapper {

	public static AuthCredentialsDTO map(User user, String token) {
		return new AuthCredentialsDTO(token, map(user));
	}
	
	public static UserCredentialsDTO map(User user) {
		return new UserCredentialsDTO(
				user.getId(),
				user.getEmail(), 
				user.getUsername(),
				user.getRole().getValue());
	}
}
