package com.barbershop.application.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.barbershop.application.DTOs.EmployeeDTO;
import com.barbershop.application.services.UserService;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
		
	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
		List<EmployeeDTO> employeeList = userService.getAllEmployees();
		return new ResponseEntity<List<EmployeeDTO>>(employeeList, HttpStatus.OK);
	}
}
