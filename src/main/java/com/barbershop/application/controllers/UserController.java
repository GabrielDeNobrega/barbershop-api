package com.barbershop.application.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.barbershop.application.DTOs.UserDTO;
import com.barbershop.application.enums.Role;
import com.barbershop.application.exceptions.custom.CustomApplicationException;
import com.barbershop.application.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> userList =  userService.getAllUsers();
		return new ResponseEntity<List<UserDTO>>(userList, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDto) {
		userHasRole(Role.CUSTOMER, userDto.role);
		UserDTO createdUser = userService.addUser(userDto);
		return new ResponseEntity<UserDTO>(createdUser, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	@PostMapping("/register/employee")
	public ResponseEntity<UserDTO> addEmployee(@RequestBody UserDTO userDto) {
		userHasRole(Role.EMPLOYEE, userDto.role);
		UserDTO createdUser = userService.addUser(userDto);
		return new ResponseEntity<UserDTO>(createdUser, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	@PostMapping("/register/administrator")
	public ResponseEntity<UserDTO> addAdministrator(@RequestBody UserDTO userDto) {
		userHasRole(Role.ADMINISTRATOR, userDto.role);
		UserDTO createdUser = userService.addUser(userDto);
		return new ResponseEntity<UserDTO>(createdUser, HttpStatus.CREATED);
	}
	
	public void userHasRole(Role role, String userRole) {
		if(!userRole.equalsIgnoreCase(role.getValue())) 
			throw CustomApplicationException.badRequest(
					String.format("%s must have '%s' role", role.getValue(), role));
	}
}
