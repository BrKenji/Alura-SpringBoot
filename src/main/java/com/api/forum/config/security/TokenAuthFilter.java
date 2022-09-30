package com.api.forum.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.forum.model.Usuario;
import com.api.forum.repository.UsuarioRepository;
import com.api.forum.services.TokenService;

// This filter intercepts the request to seize the token, validate and authorize it for future requests 

// Em filters não é possível a injeção de dependencias, portanto a notação autowired
// não pode ser usada
public class TokenAuthFilter extends OncePerRequestFilter{

	private TokenService tokenService;
	
	private UsuarioRepository usuarioRepository;
	
	public TokenAuthFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	// This method uses a logic to capture the token from the request header
	// and authenticate the user/request
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// Retrieve header token
		String token = retriveToken(request);
		// Validate token
		boolean valid = tokenService.isTokenValid(token);
		
		if (valid) {
			authenticateClient(token);
		}
		
		filterChain.doFilter(request, response);
		
	}

	// Unlike the AuthManager, here the password/email auth has been already authorized, here we want spring to
	// auth the user for whatever operation he's allowed.
	// Note: The AuthenticationManager is used to deal with the authentication via username and password
	private void authenticateClient(String token) {
		Long idUser = tokenService.getUserId(token);
		Usuario user = usuarioRepository.findById(idUser).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String retriveToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		} else {
			return token.substring(7, token.length());
		}
	}

}
