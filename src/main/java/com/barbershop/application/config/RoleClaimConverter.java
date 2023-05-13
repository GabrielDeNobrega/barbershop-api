package com.barbershop.application.config;

import java.util.ArrayList;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;


public class RoleClaimConverter implements Converter<Jwt, AbstractAuthenticationToken> {

	private final JwtGrantedAuthoritiesConverter wrapperConverter;
	
	public RoleClaimConverter(JwtGrantedAuthoritiesConverter wrapperConverter) {
		this.wrapperConverter = wrapperConverter;
	}

	@Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        var grantedAuthorities = new ArrayList<>(wrapperConverter.convert(jwt));
       
        String role = jwt.getClaims().get("role").toString();
        
        if (role != null) {
             grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        
        return new JwtAuthenticationToken(jwt, grantedAuthorities);
    }
}
