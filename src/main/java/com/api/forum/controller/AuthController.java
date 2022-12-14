package com.api.forum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.forum.dto.TokenDto;
import com.api.forum.form.LoginForm;
import com.api.forum.services.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	// The AuthManager is used to trigger the authentification via username and password
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> authClient(@RequestBody @Valid LoginForm form ){
		UsernamePasswordAuthenticationToken loginData = form.converter();
		
		try {
			Authentication authentication = authManager.authenticate(loginData);
			// Generate token and store it on String variable
			String token = tokenService.generateToken(authentication);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
			
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		

	}
	
}
