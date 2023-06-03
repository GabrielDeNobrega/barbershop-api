package com.barbershop.application.controllers;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.barbershop.application.DTOs.AppointmentDTO;
import com.barbershop.application.DTOs.AppointmentTimeDTO;
import com.barbershop.application.helpers.LoggedUser;
import com.barbershop.application.services.AppointmentService;
import com.barbershop.application.services.AppointmentTimeService;

@RestController
@RequestMapping(value = "/appointment")
public class AppointmentController {

	@Autowired
	AppointmentService appointmentService;

	@Autowired
	AppointmentTimeService appointmentTimeService;

	@PostMapping("/create")
	public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {

		AppointmentDTO createdAppointmentDTO = appointmentService.create(appointmentDTO, LoggedUser.getId());
		return new ResponseEntity<AppointmentDTO>(createdAppointmentDTO, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<AppointmentDTO>> getAllAppointment() {
		return new ResponseEntity<List<AppointmentDTO>>(appointmentService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/available-times/{employeeId}/{day}")
	public ResponseEntity<List<AppointmentTimeDTO>> getAllAppointmentAvailableTimes(@PathVariable Long employeeId,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date day) {
		List<AppointmentTimeDTO> appointmentTimes = appointmentTimeService.getAvailableAppointmentTimes(employeeId, day);
		return new ResponseEntity<List<AppointmentTimeDTO>>(appointmentTimes, HttpStatus.OK);
	}
	
	@PutMapping("/status/{appointmentId}/{status}")
	public ResponseEntity<AppointmentDTO> changeAppointmentStatus(@PathVariable Long appointmentId, @PathVariable String status ) {
		AppointmentDTO appointmentDTO = 
				appointmentService.changeAppointmentStatus(appointmentId, status);
		return new ResponseEntity<AppointmentDTO>(appointmentDTO, HttpStatus.OK);
	}
}
