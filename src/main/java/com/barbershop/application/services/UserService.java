package com.barbershop.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbershop.application.DTOs.UserDTO;
import com.barbershop.application.entities.User;
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
}
