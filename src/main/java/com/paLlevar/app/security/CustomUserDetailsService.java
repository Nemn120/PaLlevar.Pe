package com.paLlevar.app.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.UserEntity;
import com.paLlevar.app.model.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	/*	UserEntity user = repo.findOneByUsername(username);
		if(user != null) {
			return UserPrincipal.create(user);
		}
		return null;
		
		*/
		
		UserEntity user = repo.findOneByUsername(username); //from usuario where nombre := username
		
		if (user == null) {
			throw new UsernameNotFoundException(String.format("Usuario no existe", username));
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();
		
		
			roles.add(new SimpleGrantedAuthority(user.getProfile().getShortDescription()));
		
		
		UserDetails userDetails = new User(user.getNombre(), user.getPassword(), roles);
		
		return userDetails;
	}
	
}
