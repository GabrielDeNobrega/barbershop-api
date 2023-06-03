package com.barbershop.application.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.barbershop.application.DTOs.AppointmentDTO;
import com.barbershop.application.entities.Appointment;
import com.barbershop.application.entities.Service;
import com.barbershop.application.entities.User;
import com.barbershop.application.enums.AppointmentStatus;
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

	public Page<AppointmentDTO> getUserAppointments(Long userId, Integer pageNumber, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNumber, pageSize);
		Page<Appointment> userAppointments = appointmentRespository.findByCustomerIdOrderByDesc(userId, paging);
		return AppointmentMapper.reverseMap(userAppointments);
	}

	public Page<AppointmentDTO> getEmployeeAppointments(Long employeeId, Integer pageNumber, Integer pageSize) {

		Pageable paging = PageRequest.of(pageNumber, pageSize);

		Page<Appointment> userAppointments = appointmentRespository.findByEmployeeIdOrderByDesc(employeeId, paging);

		return AppointmentMapper.reverseMap(userAppointments);
	}

	public AppointmentDTO changeAppointmentStatus(Long appointmentId, String status) {
		Appointment appointment = appointmentRespository.findById(appointmentId)
				.orElseThrow(() -> CustomApplicationException.notFound("Appointment not found"));

		if (appointment.getStatus().equals(AppointmentStatus.CANCELED))
			throw CustomApplicationException
					.badRequest(String.format("Appointment is cancelled and cannot be changed"));

		AppointmentStatus appointmentStatus = AppointmentStatus.get(status);
		
		if (appointmentStatus.equals(AppointmentStatus.UNDEFINED))
			throw CustomApplicationException.badRequest(String.format("Unexprected appointment status provided"));

		appointment.setStatus(appointmentStatus);

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
