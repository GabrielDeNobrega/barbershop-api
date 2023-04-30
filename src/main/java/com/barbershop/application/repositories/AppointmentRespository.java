package com.barbershop.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.barbershop.application.entities.Appointment;

public interface AppointmentRespository extends JpaRepository<Appointment, Long> {
	
}
