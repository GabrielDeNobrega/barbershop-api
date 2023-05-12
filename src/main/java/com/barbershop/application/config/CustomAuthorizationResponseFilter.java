package com.barbershop.application.config;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import com.barbershop.application.core.base.classes.ErrorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthorizationResponseFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
			FilterChain filterChain) throws ServletException, IOException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		System.out.println(response.getStatus());

		filterChain.doFilter(request, response);

		System.out.println(response.getStatus());
		if (response.getStatus() == HttpStatus.UNAUTHORIZED.value()) {
			buildResponseError("You are not authorized to perform this action",
					response, HttpStatus.UNAUTHORIZED);		
		}
		else if (response.getStatus() == HttpStatus.FORBIDDEN.value())
			buildResponseError("You do not have permission to perform this action",
					response, HttpStatus.FORBIDDEN);
		
	}
	
	private void buildResponseError (
			String messageError, 
			HttpServletResponse response, 
			HttpStatus httpStatus ) throws IOException {
		response.setContentType("application/json");
		response.setStatus(httpStatus.value());
		response.getWriter().write(mountErrorMessage(messageError, httpStatus));
	}
	
	private String mountErrorMessage(String messageError, HttpStatus status) throws JsonProcessingException {
		return new ObjectMapper()
				.writeValueAsString(
						new ErrorMessage(messageError, status, status.value()));
	}
}
