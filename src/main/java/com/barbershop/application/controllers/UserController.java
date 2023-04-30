package com.barbershop.application.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.barbershop.application.DTOs.UserDTO;
import com.barbershop.application.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> userList =  userService.getAllUsers();
		return new ResponseEntity<List<UserDTO>>(userList, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user) {
		UserDTO createdUser = userService.addUser(user);
		return new ResponseEntity<UserDTO>(createdUser, HttpStatus.CREATED);
	}
}
