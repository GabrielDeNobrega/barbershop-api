package com.barbershop.application.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.barbershop.application.DTOs.ServiceDTO;
import com.barbershop.application.entities.Service;
import com.barbershop.application.exceptions.custom.CustomApplicationException;
import com.barbershop.application.mappers.ServiceMapper;
import com.barbershop.application.repositories.ServiceRepository;

@org.springframework.stereotype.Service
public class ServiceService {

	@Autowired
	private ServiceRepository serviceRepository;

	public List<ServiceDTO> getAllServices() {
		return ServiceMapper.reverseMap(serviceRepository.findAll());
	}
	
	public ServiceDTO getServiceById(Long serviceId) {
		return ServiceMapper.reverseMap(serviceRepository.findById(serviceId)
				.orElseThrow(()-> CustomApplicationException.notFound("Service not found")));
	}

	public ServiceDTO addService(ServiceDTO service) {
		Service createdService = serviceRepository.save(ServiceMapper.map(service));
		return ServiceMapper.reverseMap(createdService);
	}
}
