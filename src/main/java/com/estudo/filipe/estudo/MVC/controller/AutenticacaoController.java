package com.estudo.filipe.estudo.MVC.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudo.filipe.estudo.MVC.config.security.TokenService;
import com.estudo.filipe.estudo.MVC.controller.dto.TokenDto;
import com.estudo.filipe.estudo.MVC.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	final AuthenticationManager authManager;
	final TokenService tokenService;

	public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService) {
		this.authManager = authenticationManager;
		this.tokenService = tokenService;
	}

	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			
			String token = tokenService.gerarToken(authentication);

			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}

	}
}
