package com.barbershop.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.barbershop.application.entities.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {

}
