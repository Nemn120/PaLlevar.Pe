package com.paLlevar.app.model.services.impl;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.MenuDayEntity;
import com.paLlevar.app.model.repository.MenuDayRepository;
import com.paLlevar.app.model.services.MenuDayService;

@Service
public class MenuDayServiceImpl implements MenuDayService {
	
	@Autowired
	private MenuDayRepository repo;

	@Override
	public List<MenuDayEntity> getAll() {
		return repo.findAll();
	}

	@Override
	public MenuDayEntity getOneById(Integer id) {
		LocalTime localTime = LocalTime.now();
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

	@Override
	public MenuDayEntity getMenuDayByDayAndOrganizationIdAndSucursalId(Integer sucursalId, Integer organizationId) {
		LocalTime localTime = LocalTime.now();
		localTime.of(0, 0);
		
		Date date = new Date();
		
		//MenuDayEntity menuDay = repo.getMenuDayByDayAndOrganizationIdAndSucursalId(date,sucursalId, organizationId);
		return null;
		
	}


}
