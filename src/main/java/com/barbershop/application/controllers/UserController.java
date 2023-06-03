package com.barbershop.application.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.barbershop.application.DTOs.AppointmentDTO;
import com.barbershop.application.DTOs.UserDTO;
import com.barbershop.application.core.base.classes.RoleChecker;
import com.barbershop.application.enums.Role;
import com.barbershop.application.helpers.LoggedUser;
import com.barbershop.application.services.AppointmentService;
import com.barbershop.application.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController extends RoleChecker{

	@Autowired
	private UserService userService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDto) {
		userHasRole(Role.CUSTOMER, userDto.role);
		UserDTO createdUser = userService.addUser(userDto);
		return new ResponseEntity<UserDTO>(createdUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/appointments")
	public ResponseEntity<Page<AppointmentDTO>> getAppointmentHistory(
			@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		Page<AppointmentDTO> appointments = 
				appointmentService.getUserAppointments(LoggedUser.getId(), pageNumber, pageSize);
		return new ResponseEntity<Page<AppointmentDTO>>(appointments, HttpStatus.OK);
	}
}
