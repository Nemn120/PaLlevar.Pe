package com.paLlevar.app.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.MenuOptionEntity;
import com.paLlevar.app.model.repository.MenuOptionRepository;
import com.paLlevar.app.model.services.MenuOptionService;
@Service
public class MenuOptionServiceImpl implements MenuOptionService{

	
	@Autowired
	private MenuOptionRepository repo;
	
	@Override
	public List<MenuOptionEntity> getAll() {
		return repo.findAll();
	}

	@Override
	public MenuOptionEntity getOneById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MenuOptionEntity save(MenuOptionEntity t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
