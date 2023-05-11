package com.barbershop.application.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import com.barbershop.application.DTOs.UserCredentialsDTO;
import com.barbershop.application.entities.User;
import com.barbershop.application.exceptions.custom.CustomApplicationException;
import com.barbershop.application.mappers.AuthMapper;
import com.barbershop.application.repositories.UserRepository;


@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	private final JwtEncoder jwtEncoder;

	public AuthService(JwtEncoder jwtEncoder) {
		this.jwtEncoder = jwtEncoder;
	}

	public UserCredentialsDTO authenticate(UserCredentialsDTO userCredentialsDTO) {

		User user = userRepository.findByEmail(userCredentialsDTO.email)
				.orElseThrow(() -> new CustomApplicationException("User not found", HttpStatus.NOT_FOUND));
		
		String token = generateToken(user);
		
		if(token == null)
			new CustomApplicationException("Unable to gerenate token", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return AuthMapper.map(user, token);
	}

	public String generateToken(User user) {
		Instant now = Instant.now();
		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer("self")
				.issuedAt(now)
				.expiresAt(now.plus(8, ChronoUnit.HOURS))
				.subject(user.getEmail())
				.claim("id", user.getId())
				.claim("role", user.getRole())
				.build();
		
		return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}
}
