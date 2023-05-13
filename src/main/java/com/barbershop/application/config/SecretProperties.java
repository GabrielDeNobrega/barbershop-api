package com.barbershop.application.config;

import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "secret")
public class SecretProperties{
	
	private RSAPublicKey publicKey;
	private RSAPrivateKey privateKey;
	
	public RSAPublicKey getPublicKey() {
		return publicKey;
	}
	
	public void setPublicKey(RSAPublicKey publicKey) {
		this.publicKey = publicKey;
	}
	
	public RSAPrivateKey getPrivateKey() {
		return privateKey;
	}
	
	public void setPrivateKey(RSAPrivateKey privateKey) {
		this.privateKey = privateKey;
	}
}
