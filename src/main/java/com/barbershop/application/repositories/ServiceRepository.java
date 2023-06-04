package com.barbershop.application.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.barbershop.application.entities.Service;

public interface ServiceRepository extends JpaRepository<Service, Long>, PagingAndSortingRepository<Service, Long> {

	@Query("SELECT s FROM Service s WHERE LOWER(s.name) = LOWER(:name) ")
	public Service findByNameContainingIgnoreCase(@Param("name") String name);
	
	public List<Service> findByActiveTrue();

	@Query("SELECT s FROM Service s ORDER BY s.createdAt DESC")
	public Page<Service> findAllOrderByCreatedAtDesc(Pageable pageable);
	
	
}
