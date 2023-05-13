package com.barbershop.application.mappers;

import com.barbershop.application.DTOs.UserCredentialsDTO;
import com.barbershop.application.entities.User;

public class AuthMapper {

	public static UserCredentialsDTO map(User user, String token) {
		return new UserCredentialsDTO(user.getId(), user.getEmail(), token);
	}
}
