package com.barbershop.application.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barbershop.application.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String name);
	Optional<User> findByEmail(String email);
}
