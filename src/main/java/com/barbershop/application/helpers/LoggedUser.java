package com.barbershop.application.helpers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class LoggedUser {
	public static Long getId() {
	    JwtAuthenticationToken authToken = 
	    		(JwtAuthenticationToken) SecurityContextHolder
	    		.getContext()
	    		.getAuthentication();
	    
	    return (Long) authToken.getTokenAttributes().get("id");
	}
}
