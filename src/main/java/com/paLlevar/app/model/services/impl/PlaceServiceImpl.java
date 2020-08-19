package com.paLlevar.app.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paLlevar.app.model.entities.PlaceEntity;
import com.paLlevar.app.model.repository.PlaceRepository;
import com.paLlevar.app.model.services.PlaceService;

@Service
public class PlaceServiceImpl implements PlaceService{
	
	@Autowired
	PlaceRepository repo;

	@Override
	public List<PlaceEntity> getAll() {
		return repo.findAll();
	}

	@Override
	public PlaceEntity getOneById(Integer id) {
		return repo.findById(id).orElse(new PlaceEntity());
	}

	@Override
	public PlaceEntity save(PlaceEntity t) {
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
		
	}
	

}
