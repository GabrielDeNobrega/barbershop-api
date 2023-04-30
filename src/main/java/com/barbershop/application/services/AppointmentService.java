package com.barbershop.application.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

	public AppointmentDTO create(AppointmentDTO appointmentDTO) {

		Service service = serviceRepository.findById(appointmentDTO.serviceId)
				.orElseThrow(() -> new CustomApplicationException("Service type not found", HttpStatus.NOT_FOUND));

		User custumer = userRepository.findById(appointmentDTO.custumerId)
				.orElseThrow(() -> new CustomApplicationException("Could not find custumer", HttpStatus.NOT_FOUND));

		User employee = userRepository.findById(appointmentDTO.employeeId)
				.orElseThrow(() -> new CustomApplicationException("Could not find employee", HttpStatus.NOT_FOUND));

		Appointment appointment = AppointmentMapper.map(appointmentDTO);

		appointment.ConfigureAppointment(custumer, employee, service);

		appointmentRespository.save(appointment);

		return AppointmentMapper.reverseMap(appointment);
	}
}