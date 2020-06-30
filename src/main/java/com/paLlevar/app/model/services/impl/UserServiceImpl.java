package com.paLlevar.app.model.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.ProfileEntity;
import com.paLlevar.app.model.entities.ProfileMenuOptionEntity;
import com.paLlevar.app.model.entities.UserEntity;
import com.paLlevar.app.model.repository.UserRepository;
import com.paLlevar.app.model.services.ProfileMenuOptionService;
import com.paLlevar.app.model.services.ProfileService;
import com.paLlevar.app.model.services.UserService;
import com.paLlevar.app.security.UserPrincipal;

@Service
public class UserServiceImpl implements UserService {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private ProfileMenuOptionService profileMenuOptionService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	public List<UserEntity> getAll() {
		return repo.findAll();
	}

	@Override
	public UserEntity getOneById(Integer id) {
		return repo.getOne(id);
	}

	@Override
	public UserEntity save(UserEntity t) {
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public UserEntity registerUserByProfile(UserEntity user) {
		user.setPassword(bcrypt.encode(user.getPassword()));
		return repo.save(user);
	}

	@Override
	public UserEntity getUserByUsername(String username) {
		return repo.findOneByUsername(username);
	}

	
	
	

}
