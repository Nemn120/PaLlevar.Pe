package com.paLlevar.app.model.services.impl;

import java.util.List;

import com.paLlevar.app.model.entities.MenuDayProductEntity;
import com.paLlevar.app.model.repository.MenuDayProductRepository;
import com.paLlevar.app.model.services.MenuDayProductService;

public class MenuDayProductServiceImpl implements MenuDayProductService {
	
	private MenuDayProductRepository repo;

	@Override
	public List<MenuDayProductEntity> getAll() {
		return repo.findAll();
	}

	@Override
	public MenuDayProductEntity getOneById(Integer id) {
		return repo.getOne(id);
	}

	@Override
	public MenuDayProductEntity save(MenuDayProductEntity t) {
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

}
