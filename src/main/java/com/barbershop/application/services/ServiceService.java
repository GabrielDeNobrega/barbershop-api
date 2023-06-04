package com.barbershop.application.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		List<Service> serviceList = serviceRepository.findByActiveTrue();
		return ServiceMapper.reverseMap(serviceList);
	}

	public Page<ServiceDTO> getAllServices(Integer pageNumber, Integer pageSize) {
		Page<Service> serviceList = serviceRepository
				.findAllOrderByCreatedAtDesc(Pageable.ofSize(pageSize).withPage(pageNumber));
		return ServiceMapper.reverseMap(serviceList);
	}

	public ServiceDTO getServiceById(Long serviceId) {
		return ServiceMapper.reverseMap(serviceRepository.findById(serviceId)
				.orElseThrow(() -> CustomApplicationException.notFound("Service not found")));
	}

	public ServiceDTO addService(ServiceDTO service) {

		Service existentServiceByName = serviceRepository.findByNameContainingIgnoreCase(service.name);

		if (existentServiceByName != null)
			throw CustomApplicationException.badRequest("A Service with this name already exists");

		Service createdService = serviceRepository.save(ServiceMapper.map(service));
		return ServiceMapper.reverseMap(createdService);
	}

	public ServiceDTO changeServiceStatus(Long serviceId) {
		
		List<Service> activeServiceList = serviceRepository.findByActiveTrue();
		
		if(activeServiceList.size() == 1 && activeServiceList.get(0).getId() == serviceId)
			throw CustomApplicationException.badRequest("At least one service must remain active");

		if(serviceId == null)
			throw CustomApplicationException.badRequest("A service Id must be specified");
		
		Service service = serviceRepository.findById(serviceId)
				.orElseThrow(() -> CustomApplicationException.notFound("Service not found"));

		service.setActive(!service.getActive());
		
		Service updatedService = serviceRepository.save(service);
		
		return ServiceMapper.reverseMap(updatedService);
	}
}
