package com.barbershop.application.mappers;

import java.util.List;
import com.barbershop.application.DTOs.UserDTO;
import com.barbershop.application.entities.User;
import com.barbershop.application.enums.Role;

public class UserMapper {
	
	public static User map(UserDTO userDto) {
		return new User( 
				userDto.username,  
				userDto.cpf,  
				userDto.email, 
				userDto.birth,  
				userDto.active,
				Role.get(userDto.role));
	}
	
	public static List<User> map(List<UserDTO> userDtoList) {
		return userDtoList.stream().map(x -> map(x)).toList();
	}
	
	public static UserDTO reverseMap(User user) {
		return new UserDTO( 
				user.getId(),  
				user.getUsername(),  
				user.getCpf(),  
				user.getEmail(), 
				user.getBirth(),  
				user.getActive(),
				user.getRole().getValue());
	}
	
	public static List<UserDTO> reverseMap(List<User> userDtoList) {
		return userDtoList.stream().map(x -> reverseMap(x)).toList();
	}
}
