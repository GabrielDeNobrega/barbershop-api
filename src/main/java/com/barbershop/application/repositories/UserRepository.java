package com.barbershop.application.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.barbershop.application.entities.User;
import com.barbershop.application.enums.Role;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	Optional<User> findByCpf(String cpf);
	Optional<List<User>> findAllByRoleAndDeletedAtNull(Role role);
}
