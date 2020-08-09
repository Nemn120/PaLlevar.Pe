package com.paLlevar.app.model.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paLlevar.app.model.entities.UserEntity;
import com.paLlevar.app.model.repository.UserRepository;
import com.paLlevar.app.model.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PasswordEncoder  bcrypt;

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
		if(t.getPhoto() != null &&  t.getPhoto().length>0) {
			repo.updatePhoto(t.getId(),t.getPhoto());
		}
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

	@Override
	public List<UserEntity> getUserListByProfileANDStatus(UserEntity user) {
		return repo.getUserListByStatusAndProfileID(user.getStatus(),user.getOrganizationId(),user.getProfile().getIdProfile());
	}

	@Override
	public List<UserEntity> getUserListByOrganizationId(Integer idOrg) {
		return repo.findByOrganizationId(idOrg);
	}

	@Override
	public void updateStatusById(Integer id, String status) {
		repo.updateStatusById(id, status);
		
	}

	
	
	

}
