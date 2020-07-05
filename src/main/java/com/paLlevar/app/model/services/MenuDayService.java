package com.paLlevar.app.model.services;

import java.util.List;

import com.paLlevar.app.model.entities.MenuDayEntity;

public interface MenuDayService extends GenericCRUD<MenuDayEntity, Integer>{
	
	public List<MenuDayEntity> getMenuDayListByFields(MenuDayEntity menuDay);
	public MenuDayEntity editMenuDay(MenuDayEntity menuDay);
	
}
