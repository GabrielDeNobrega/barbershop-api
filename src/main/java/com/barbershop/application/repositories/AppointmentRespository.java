package com.barbershop.application.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.barbershop.application.entities.Appointment;
import com.barbershop.application.enums.AppointmentStatus;

public interface AppointmentRespository
		extends JpaRepository<Appointment, Long>, PagingAndSortingRepository<Appointment, Long> {

	@Query("SELECT a FROM Appointment a " + "WHERE a.employee.id = ?1 " + "ORDER BY a.createdAt DESC")
	public Page<Appointment> findByEmployeeIdOrderByDesc(Long employeeId, Pageable pageable);

	@Query("SELECT a FROM Appointment a " + "WHERE a.customer.id = ?1 " + "ORDER BY a.createdAt DESC")
	public Page<Appointment> findByCustomerIdOrderByDesc(Long customerId, Pageable pageable);

	@Query("SELECT a FROM Appointment a " + "WHERE a.employee.id = ?1 AND a.status != \"CANCELED\" "
			+ "AND CAST(a.createdAt AS date) = CAST(?2 AS date)")
	public List<Appointment> findByEmployeeIdAndCreatedAt(Long employeeId, Date createdAt);

	@Query("SELECT a FROM Appointment a " 
			+ "WHERE a.status = :status "
			+ "AND CAST(a.createdAt AS DATE) BETWEEN :startDate AND :endDate " 
			+ "ORDER BY a.createdAt DESC")
	public Page<Appointment> findAllOrderByCreatedAtDesc(
			@Param("status") AppointmentStatus appointmentStatus,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			Pageable pageable);
}
