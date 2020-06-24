package com.paLlevar.app.model.services.impl;

import java.util.List;

import com.paLlevar.app.model.entities.MenuDayEntity;
import com.paLlevar.app.model.repository.MenuDayRepository;
import com.paLlevar.app.model.services.MenuDayService;

public class MenuDayServiceImpl implements MenuDayService {
	
	private MenuDayRepository repo;

	@Override
	public List<MenuDayEntity> getAll() {
		return repo.findAll();
	}

	@Override
	public MenuDayEntity getOneById(Integer id) {
		return repo.getOne(id);
	}

	@Override
	public MenuDayEntity save(MenuDayEntity t) {
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

}
