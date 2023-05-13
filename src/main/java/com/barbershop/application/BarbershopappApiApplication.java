package com.barbershop.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.barbershop.application.config.SecretProperties;

@SpringBootApplication
@EnableConfigurationProperties(SecretProperties.class)
public class BarbershopappApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarbershopappApiApplication.class, args);
	}
}

