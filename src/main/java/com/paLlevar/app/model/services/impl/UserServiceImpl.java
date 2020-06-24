package com.paLlevar.app.model.services.impl;

import java.util.List;

import com.paLlevar.app.model.entities.UserEntity;
import com.paLlevar.app.model.repository.UserRepository;
import com.paLlevar.app.model.services.UserService;

public class UserServiceImpl implements UserService {
	
	private UserRepository repo;

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

}
