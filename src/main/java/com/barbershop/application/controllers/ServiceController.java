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
import com.barbershop.application.DTOs.ServiceDTO;
import com.barbershop.application.services.ServiceService;

@RestController
@RequestMapping(value = "/service")
public class ServiceController {
	@Autowired
	private ServiceService serviceService;
	
	@GetMapping("/all")
	public ResponseEntity<List<ServiceDTO>> getAllServices() {
		List<ServiceDTO> serviceList = serviceService.getAllServices();
		return new ResponseEntity<List<ServiceDTO>>(serviceList, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<ServiceDTO> addService(@RequestBody ServiceDTO user) {
		ServiceDTO createdService = serviceService.addService(user);
		return new ResponseEntity<ServiceDTO>(createdService, HttpStatus.CREATED);
	}
}
