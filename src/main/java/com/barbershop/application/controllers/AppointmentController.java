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
import com.barbershop.application.DTOs.AppointmentDTO;
import com.barbershop.application.services.AppointmentService;

import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping(value = "/appointment")
public class AppointmentController {

	@Autowired
	AppointmentService appointmentService;

	@PostMapping("/create")
	public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
		AppointmentDTO createdAppointmentDTO = appointmentService.create(appointmentDTO);
		return new ResponseEntity<AppointmentDTO>(createdAppointmentDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<AppointmentDTO>> getAllAppointment() {
		return new ResponseEntity<List<AppointmentDTO>>(appointmentService.getAll(), HttpStatus.OK);
	}
	
}
