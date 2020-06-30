package com.paLlevar.app.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.ProfileMenuOptionEntity;
import com.paLlevar.app.model.repository.ProfileMenuOptionRepository;
import com.paLlevar.app.model.services.ProfileMenuOptionService;


@Service
public class ProfileMenuOptionServiceImpl implements ProfileMenuOptionService{

	@Autowired
	private ProfileMenuOptionRepository repo;

	@Override
	public List<ProfileMenuOptionEntity> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfileMenuOptionEntity getOneById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfileMenuOptionEntity save(ProfileMenuOptionEntity t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProfileMenuOptionEntity> getOptionsByProfileId(Integer profileId) {
		return repo.getOptionsByProfileId(profileId);
	}
	
	
	
	
}
