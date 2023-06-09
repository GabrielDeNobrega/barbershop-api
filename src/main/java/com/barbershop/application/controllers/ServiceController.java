package com.barbershop.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/all/paginated")
	public ResponseEntity<Page<ServiceDTO>> getAllServices(
			@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		Page<ServiceDTO> serviceList = serviceService.getAllServices(pageNumber, pageSize);
		return new ResponseEntity<Page<ServiceDTO>>(serviceList, HttpStatus.OK);
	}

	@GetMapping("/{serviceId}")
	public ResponseEntity<ServiceDTO> getServiceById(@PathVariable Long serviceId) {
		ServiceDTO serviceList = serviceService.getServiceById(serviceId);
		return new ResponseEntity<ServiceDTO>(serviceList, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<ServiceDTO> addService(@RequestBody ServiceDTO user) {
		ServiceDTO createdService = serviceService.addService(user);
		return new ResponseEntity<ServiceDTO>(createdService, HttpStatus.CREATED);
	}
	
	@PutMapping("/status/change/{serviceId}")
	public ResponseEntity<ServiceDTO> changeServiceStatus(@PathVariable Long serviceId) {
		ServiceDTO createdService = serviceService.changeServiceStatus(serviceId);
		return new ResponseEntity<ServiceDTO>(createdService, HttpStatus.CREATED);
	}
}
