package com.barbershop.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barbershop.application.DTOs.UserDTO;
import com.barbershop.application.entities.User;
import com.barbershop.application.exceptions.custom.CustomApplicationException;
import com.barbershop.application.formatters.CpfFormatter;
import com.barbershop.application.mappers.UserMapper;
import com.barbershop.application.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserDTO> getAllUsers(){
		return UserMapper.reverseMap(userRepository.findAll());
	}
	
	public UserDTO addUser(UserDTO userDto){
		
		if(userRepository.findByCpf(CpfFormatter.format(userDto.cpf)).orElse(null) != null)
			throw CustomApplicationException.badRequest("A user with this CPF already exists");
		
		if(userRepository.findByEmail(userDto.email).orElse(null) != null)
			throw CustomApplicationException.badRequest("A user with this email already exists");
		
		User createdUser = userRepository.save(UserMapper.map(userDto));
		return UserMapper.reverseMap(createdUser);
	}
	
	public UserDTO getUser(String username){
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> CustomApplicationException.badRequest("Could not find any customer with specified name"));
		
		return UserMapper.reverseMap(user);
	}
}
