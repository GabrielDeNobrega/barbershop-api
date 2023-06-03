package com.barbershop.application.mappers;

import java.util.List;

import org.springframework.data.domain.Page;

import com.barbershop.application.DTOs.AppointmentDTO;
import com.barbershop.application.entities.Appointment;

public class AppointmentMapper {
	public static Appointment map(AppointmentDTO appointmentDto) {
		return new Appointment( 
				appointmentDto.start,  
				appointmentDto.end);
	}
	
	public static List<Appointment> map(List<AppointmentDTO> appointmentDtoList) {
		return appointmentDtoList.stream().map(x -> map(x)).toList();
	}
	
	public static AppointmentDTO reverseMap(Appointment appointment) {
		return new AppointmentDTO( 
				appointment.getId(),  
				appointment.getStart(),  
				appointment.getEnd(),  
				UserMapper.reverseMap(appointment.getCustomer()), 
				UserMapper.reverseMap(appointment.getEmployee()),  
				ServiceMapper.reverseMap(appointment.getService()),
				appointment.getCreatedAt(),
				appointment.getStatus().getValue());
	}
	
	public static List<AppointmentDTO> reverseMap(List<Appointment> appointmentDtoList) {
		return appointmentDtoList.stream().map(x -> reverseMap(x)).toList();
	}
	
	public static Page<AppointmentDTO> reverseMap(Page<Appointment> appointmentDtoPage) {
		return appointmentDtoPage.map(x -> reverseMap(x));
	}
}
