package com.api.forum.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.forum.model.Usuario;
import com.api.forum.repository.UsuarioRepository;

@Service
public class AuthService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepo.findByEmail(username);
		if(usuario.isPresent()) {
			return usuario.get();
		} else {
			throw new UsernameNotFoundException("Dados Inválidos!");
		}
	}

}
