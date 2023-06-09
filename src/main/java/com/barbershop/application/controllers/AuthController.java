package com.barbershop.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbershop.application.DTOs.AuthCredentialsDTO;
import com.barbershop.application.DTOs.UserCredentialsDTO;
import com.barbershop.application.services.AuthService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@PostMapping("/authenticate")
	public ResponseEntity<AuthCredentialsDTO> token(@RequestBody UserCredentialsDTO userCredentialsDTO) {
		AuthCredentialsDTO authCredentials = authService.authenticate(userCredentialsDTO);
		return new ResponseEntity<AuthCredentialsDTO>(authCredentials, HttpStatus.OK);
	}
}
