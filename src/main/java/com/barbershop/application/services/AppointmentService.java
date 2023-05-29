package com.barbershop.application.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.barbershop.application.DTOs.AppointmentDTO;
import com.barbershop.application.DTOs.EmployeeDTO;
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
		List<Appointment> userAppointments = appointmentRespository.findByCustomerId(userId);
		return AppointmentMapper.reverseMap(userAppointments);
	}

	public Page<AppointmentDTO> getEmployeeAppointments(Long employeeId, Integer pageNumber, Integer pageSize ) {
		
		Pageable paging = PageRequest.of(pageNumber, pageSize);

		Page<Appointment> userAppointments = appointmentRespository.findByEmployeeId(employeeId, paging);
		
		
		return AppointmentMapper.reverseMap(userAppointments);
	}

	public AppointmentDTO changeAppointmentActiveStatus(Long appointmentId, boolean activeStatus) {
		Appointment appointment = appointmentRespository.findById(appointmentId)
				.orElseThrow(() -> CustomApplicationException.notFound("Appointment not found"));

		if (appointment.getActive() == activeStatus)
			throw CustomApplicationException
					.badRequest(String.format("Appointment already %s", activeStatus ? "active" : "inactive"));

		appointment.setActive(activeStatus);

		appointmentRespository.save(appointment);

		return AppointmentMapper.reverseMap(appointment);
	}
	
	public AppointmentDTO create(AppointmentDTO appointmentDTO, Long customerId) {

		if (appointmentDTO.service == null || appointmentDTO.service.id == null)
			throw CustomApplicationException.badRequest("Service type is required");

		if (appointmentDTO.employee == null || appointmentDTO.employee.id == null)
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
