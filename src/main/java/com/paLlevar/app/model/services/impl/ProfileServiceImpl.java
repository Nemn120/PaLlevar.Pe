package com.paLlevar.app.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.ProfileEntity;
import com.paLlevar.app.model.repository.ProfileRepository;
import com.paLlevar.app.model.services.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService{

	@Autowired
	private ProfileRepository repo;
	@Override
	public List<ProfileEntity> getAll() {
		return repo.findAll();
	}

	@Override
	public ProfileEntity getOneById(Integer id) {
		return repo.getOne(id);
	}

	@Override
	public ProfileEntity save(ProfileEntity t) {
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
		
	}

}
