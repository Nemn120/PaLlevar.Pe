package com.paLlevar.app.model.services;

import java.util.List;

import com.paLlevar.app.model.entities.MenuDayEntity;

public interface MenuDayService extends GenericCRUD<MenuDayEntity, Integer>{
	
	public List<MenuDayEntity> getMenuDayListByFields(MenuDayEntity menuDay);
	public MenuDayEntity editMenuDay(MenuDayEntity menuDay);
	public MenuDayEntity saveMenuDay(MenuDayEntity menuDay);
	public boolean deleteMenuDayAndProductDayList(Integer menuId);
	
	public List<MenuDayEntity> getMenuDayListByStatusAndOrgAndSuc(MenuDayEntity menuDay);
	public List<MenuDayEntity> getMenuDayListByStatusAndOrg(MenuDayEntity menuDay); 
	
	
}
