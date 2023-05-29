package com.barbershop.application.controllers;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barbershop.application.DTOs.AppointmentDTO;
import com.barbershop.application.DTOs.EmployeeDTO;
import com.barbershop.application.helpers.LoggedUser;
import com.barbershop.application.services.AppointmentService;
import com.barbershop.application.services.UserService;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	private UserService userService;

	@Autowired
	private AppointmentService appointmentService;

	@GetMapping("/all")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
		List<EmployeeDTO> employeeList = userService.getAllEmployees();
		return new ResponseEntity<List<EmployeeDTO>>(employeeList, HttpStatus.OK);
	}

	@GetMapping("/appointment/all")
	public ResponseEntity<Page<AppointmentDTO>> getAllAppointment(
			@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		return new ResponseEntity<Page<AppointmentDTO>>(appointmentService.getEmployeeAppointments(LoggedUser.getId(), pageNumber, pageSize),
				HttpStatus.OK);
	}
}
