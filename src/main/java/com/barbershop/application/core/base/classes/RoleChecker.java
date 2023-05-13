package com.barbershop.application.core.base.classes;

import com.barbershop.application.enums.Role;
import com.barbershop.application.exceptions.custom.CustomApplicationException;

public class RoleChecker {
	public void userHasRole(Role role, String userRole) {
		if (!userRole.equalsIgnoreCase(role.getValue()))
			throw CustomApplicationException.badRequest(
					String.format("%s must have '%s' role", role.getValue(), role));
	}
}
