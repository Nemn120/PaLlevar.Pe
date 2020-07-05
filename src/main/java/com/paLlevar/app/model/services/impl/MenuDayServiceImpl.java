package com.paLlevar.app.model.services.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.MenuDayEntity;
import com.paLlevar.app.model.entities.MenuDayProductEntity;
import com.paLlevar.app.model.repository.MenuDayRepository;
import com.paLlevar.app.model.services.MenuDayProductService;
import com.paLlevar.app.model.services.MenuDayService;
import com.paLlevar.app.util.Constants;

@Service
public class MenuDayServiceImpl implements MenuDayService {
	
	@Autowired
	private MenuDayRepository repo;
	
	@Autowired
	private MenuDayProductService menuDayProductService;
	
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
		if(t.getMenuDayProduct() != null) {
			t.getMenuDayProduct().forEach(menuDayProduct ->{
				menuDayProduct.setStatus(Constants.MENUDAY_STATUS_AVAILABLE);
				menuDayProduct.setAvailable(menuDayProduct.getQuantity());
				menuDayProduct.setMenuDay(new MenuDayEntity());
				menuDayProduct.getMenuDay().setId(t.getId());
				
				menuDayProductService.save(menuDayProduct);
				t.setTotal(t.getTotal()+menuDayProduct.getPrice());
			});
		}
		/*if(t.getCountUsedMenu() != 0)
		t.setCountUsedMenu(t.getCountUsedMenu()+1);
		else
		t.setCountUsedMenu(0);
		*/	
		t.setLocalDateTime(LocalDateTime.now());
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
	
	@Override
	public List<MenuDayEntity> getMenuDayListByFields(MenuDayEntity menuDay) {
		
		return repo.getMenuDayByFields(menuDay);
	}

	@Override
	public MenuDayEntity editMenuDay(MenuDayEntity menuDay) {
		MenuDayEntity menuDayOld = repo.getOne(menuDay.getId());
		menuDayOld.getMenuDayProduct().forEach(menuDayProductOld -> {
			MenuDayProductEntity menuSearch = menuDay.getMenuDayProduct().stream()
					.filter(menu -> menu.getId().equals(menuDayProductOld.getId()))
					.findAny()
					.orElse(null);
			if(menuSearch == null) {
				menuDayProductService.deleteById(menuDayProductOld.getId());
				menuDay.getMenuDayProduct().removeIf(x -> x.getId().equals(menuDayProductOld.getId()));
			}
			
		});
		
		return null;
	}


}
