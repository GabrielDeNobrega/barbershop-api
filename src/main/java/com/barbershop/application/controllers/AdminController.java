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
import com.barbershop.application.core.base.classes.RoleChecker;
import com.barbershop.application.enums.Role;
import com.barbershop.application.services.UserService;
import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping( value = "/admin")
@RolesAllowed({"ADMINISTRATOR"})
public class AdminController extends RoleChecker{
	
	@Autowired
	private UserService userService;
	
	@GetMapping("user/all")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> userList =  userService.getAllUsers();
		return new ResponseEntity<List<UserDTO>>(userList, HttpStatus.OK);
	}
	
	@PostMapping("/register/administrator")
	public ResponseEntity<UserDTO> addAdministrator(@RequestBody UserDTO userDto) {
		userHasRole(Role.ADMINISTRATOR, userDto.role);
		UserDTO createdUser = userService.addUser(userDto);
		return new ResponseEntity<UserDTO>(createdUser, HttpStatus.CREATED);
	}
	
	@PostMapping("/register/employee")
	public ResponseEntity<UserDTO> addEmployee(@RequestBody UserDTO userDto) {
		userHasRole(Role.EMPLOYEE, userDto.role);
		UserDTO createdUser = userService.addUser(userDto);
		return new ResponseEntity<UserDTO>(createdUser, HttpStatus.CREATED);
	}
}
