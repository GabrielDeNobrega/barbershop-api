package com.barbershop.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
		jsr250Enabled = true,
		prePostEnabled = true,
		securedEnabled = true)
public class SecurityConfig {
	
	@Autowired
	private SecretProperties secretProperties;
	
	@Bean
	CustomAuthorizationResponseFilter AuthResponseFilter() {
		return new CustomAuthorizationResponseFilter();
	}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
                .authorizeHttpRequests(auth -> auth
                		.requestMatchers("/auth/**", "/user/register").permitAll()
                		.anyRequest().authenticated())
                .cors(Customizer.withDefaults())
                .oauth2ResourceServer(resourceServer -> resourceServer
                		.jwt()
                		.jwtAuthenticationConverter(
                				new RoleClaimConverter(
                						new JwtGrantedAuthoritiesConverter())))
                .httpBasic(Customizer.withDefaults())
                .addFilterAfter(AuthResponseFilter(), BasicAuthenticationFilter.class)
                .build();
    }
    
    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(secretProperties.getPublicKey()).build();
    }
    
    @Bean
    JwtEncoder jwtEncoder() {
    	  JWK jwk = new RSAKey.Builder(secretProperties.getPublicKey()).privateKey(secretProperties.getPrivateKey()).build();
    	    JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
    	    return new NimbusJwtEncoder(jwks);
    }
}