package com.barbershop.application.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.barbershop.application.entities.Appointment;

public interface AppointmentRespository extends JpaRepository<Appointment, Long>, PagingAndSortingRepository<Appointment, Long> {
	
	@Query("SELECT a FROM Appointment a "
			+ "WHERE a.employee.id = ?1 "
			+ "ORDER BY a.createdAt DESC")
	public Page<Appointment> findByEmployeeIdOrderByDesc(Long employeeId, Pageable pageable);
	
	@Query("SELECT a FROM Appointment a "
			+ "WHERE a.customer.id = ?1 "
			+ "ORDER BY a.createdAt DESC")
	public Page<Appointment> findByCustomerIdOrderByDesc(Long customerId, Pageable pageable);
	
	@Query("SELECT a FROM Appointment a "
			+ "WHERE a.employee.id = ?1 "
			+ "AND CAST(a.createdAt AS date) = CAST(?2 AS date)")
	public List<Appointment> findByEmployeeIdAndCreatedAt(Long employeeId, Date createdAt);
}
