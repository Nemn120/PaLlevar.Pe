package com.paLlevar.app.model.services.impl;

import java.util.ArrayList;
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
		
		return repo.getOne(id);
	}

	@Override
	public MenuOptionEntity save(MenuOptionEntity t) {
		
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
		
	}

	@Override
	public List<MenuOptionEntity> getListMenuOptionByProfileId(Integer profileId) {
		List<MenuOptionEntity> menus = new ArrayList<>();
		repo.getOptionsByProfileId(profileId).forEach(x -> {
			MenuOptionEntity m = new MenuOptionEntity();
			m.setIdMenu((Integer.parseInt(String.valueOf(x[0]))));
			m.setIconMenu(String.valueOf(x[1]));
			m.setNameMenu(String.valueOf(x[2]));
			m.setUrlMenu(String.valueOf(x[3]));
			
			menus.add(m);
		});
		return menus;
		
	}
	


}
