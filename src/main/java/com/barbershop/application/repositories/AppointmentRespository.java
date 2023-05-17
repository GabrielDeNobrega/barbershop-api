package com.barbershop.application.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.barbershop.application.entities.Appointment;

public interface AppointmentRespository extends JpaRepository<Appointment, Long> {
	
	@Query("SELECT a FROM Appointment a WHERE a.customer.id = ?1")
	public List<Appointment> findByCustomerId(Long customerId);
}
