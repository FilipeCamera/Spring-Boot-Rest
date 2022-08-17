package com.estudo.filipe.estudo.MVC.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


import org.springframework.security.web.SecurityFilterChain;

import com.estudo.filipe.estudo.MVC.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
@Profile("dev")
public class DevSecurityConfiguration {

	final TokenService tokenService;
	final UsuarioRepository usuarioRepository;

	public DevSecurityConfiguration(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().antMatchers("/**").permitAll()
				.and().csrf().disable();
		return http.build();
	}

}
