package com.barbershop.application.mappers;

import java.util.List;

import org.springframework.data.domain.Page;

import com.barbershop.application.DTOs.ServiceDTO;
import com.barbershop.application.entities.Service;
import com.barbershop.application.enums.KindOfCare;

public class ServiceMapper {
	
	public static Service map(ServiceDTO serviceDto) {
		return new Service( 
				serviceDto.currentPrice,  
				serviceDto.name,  
				serviceDto.duration, 
				KindOfCare.get(serviceDto.kindOfCare));
	}
	
	public static List<Service> map(List<ServiceDTO> serviceDtoList) {
		return serviceDtoList.stream().map(x -> map(x)).toList();
	}
	
	public static ServiceDTO reverseMap(Service service) {
		return new ServiceDTO( 
				service.getId(), 
				service.getCurrentPrice(),
				service.getName(),
				service.getDuration(),
				service.getKindOfCare().getValue(),
				service.getActive(),
				service.getCreatedAt());
	}
	
	public static List<ServiceDTO> reverseMap(List<Service> serviceList) {
		return serviceList.stream().map(x -> reverseMap(x)).toList();
	}
	
	public static Page<ServiceDTO> reverseMap(Page<Service> serviceList) {
		return serviceList.map(x -> reverseMap(x));
	}
}
