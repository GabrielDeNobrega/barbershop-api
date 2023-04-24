package com.barbershop.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barbershop.application.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
