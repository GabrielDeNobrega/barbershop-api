package com.barbershop.application.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.barbershop.application.DTOs.AppointmentDTO;
import com.barbershop.application.entities.Appointment;
import com.barbershop.application.entities.Service;
import com.barbershop.application.entities.User;
import com.barbershop.application.exceptions.custom.CustomApplicationException;
import com.barbershop.application.mappers.AppointmentMapper;
import com.barbershop.application.repositories.AppointmentRespository;
import com.barbershop.application.repositories.ServiceRepository;
import com.barbershop.application.repositories.UserRepository;

@org.springframework.stereotype.Service
public class AppointmentService {

	@Autowired
	private AppointmentRespository appointmentRespository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private UserRepository userRepository;

	public List<AppointmentDTO> getAll() {
		List<Appointment> appointmentList = appointmentRespository.findAll();
		return AppointmentMapper.reverseMap(appointmentList);
	}
	
	public List<AppointmentDTO> getUserAppointments(Long userId) {
		List<Appointment> userAppointments = 
				appointmentRespository.findByCustomerId(userId);
		
		return AppointmentMapper.reverseMap(userAppointments);
	}
	

	public AppointmentDTO create(AppointmentDTO appointmentDTO, Long customerId) {

		if(appointmentDTO.service == null || appointmentDTO.service.id == null) 
			throw CustomApplicationException.badRequest("Service type is required");
		
		if(appointmentDTO.employee == null || appointmentDTO.employee.id == null) 
			throw CustomApplicationException.badRequest("Employee must be selected");
		
		User customer = userRepository.findById(customerId)
				.orElseThrow(() -> CustomApplicationException.badRequest("Could not find customer"));
		
		Service service = serviceRepository.findById(appointmentDTO.service.id)
				.orElseThrow(() -> CustomApplicationException.badRequest("Service type not found"));

		User employee = userRepository.findById(appointmentDTO.employee.id)
				.orElseThrow(() -> CustomApplicationException.badRequest("Could not find employee"));

		Appointment appointment = AppointmentMapper.map(appointmentDTO);

		appointment.configureAppointment(customer, employee, service);

		appointmentRespository.save(appointment);

		return AppointmentMapper.reverseMap(appointment);
	}
}
