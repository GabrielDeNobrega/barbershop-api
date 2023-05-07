package com.barbershop.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.barbershop.application.DTOs.UserDTO;
import com.barbershop.application.entities.User;
import com.barbershop.application.exceptions.custom.CustomApplicationException;
import com.barbershop.application.mappers.UserMapper;
import com.barbershop.application.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserDTO> getAllUsers(){
		return UserMapper.reverseMap(userRepository.findAll());
	}
	
	public UserDTO addUser(UserDTO user){
		User createdUser = userRepository.save(UserMapper.map(user));
		return UserMapper.reverseMap(createdUser);
	}
	
	public UserDTO getUser(String username){
		User user = userRepository.findByName(username)
				.orElseThrow(() -> new CustomApplicationException("Could not find any customer with specified name", HttpStatus.NOT_FOUND));
		
		return UserMapper.reverseMap(user);
	}
}
