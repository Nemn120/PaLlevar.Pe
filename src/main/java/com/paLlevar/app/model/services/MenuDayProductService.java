package com.paLlevar.app.model.services;

import java.util.List;

import com.paLlevar.app.model.entities.MenuDayProductEntity;

public interface MenuDayProductService extends GenericCRUD<MenuDayProductEntity, Integer>{
	MenuDayProductEntity saveMenuDayProduct(MenuDayProductEntity menuDayProduct);
	List<MenuDayProductEntity> getMenuDayProductListByMenuIdAndSucursalIdAndOrganizationId(MenuDayProductEntity menuDayProduct);
	List<MenuDayProductEntity> getMenuDayProductListByMenuIdAndOrganizationId(MenuDayProductEntity menuDayProduct);
	public List<MenuDayProductEntity> getListMenuProductByMenuId(Integer idMenu);
	public MenuDayProductEntity getMenuByIdAndStatus(Integer id, String status);
}
