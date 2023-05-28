package com.barbershop.application.mappers;

import java.util.List;
import com.barbershop.application.DTOs.EmployeeDTO;
import com.barbershop.application.entities.User;

public class EmployeeMapper {

	public static EmployeeDTO reverseMap(User user) {
		return new EmployeeDTO(user.getId(), user.getUsername(), user.getEmail());
	}

	public static List<EmployeeDTO> reverseMap(List<User> userDtoList) {
		return userDtoList.stream().map(x -> reverseMap(x)).toList();
	}
}
