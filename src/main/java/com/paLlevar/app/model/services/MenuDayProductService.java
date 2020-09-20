package com.paLlevar.app.model.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.MenuDayProductEntity;

@Service
public interface MenuDayProductService extends GenericCRUD<MenuDayProductEntity, Integer>{
	MenuDayProductEntity saveEditMenuDayProduct(MenuDayProductEntity menuDayProduct);
	List<MenuDayProductEntity> getMenuDayProductListByMenuIdAndSucursalIdAndOrganizationId(MenuDayProductEntity menuDayProduct);
	List<MenuDayProductEntity> getMenuDayProductListByMenuIdAndOrganizationId(MenuDayProductEntity menuDayProduct);
	public List<MenuDayProductEntity> getListMenuProductByMenuId(Integer idMenu);
	public MenuDayProductEntity getMenuByIdAndStatus(Integer id, String status);
	public List<MenuDayProductEntity> getListByOrganizationIdAndStatus(Integer id, String status);
	public List<MenuDayProductEntity> getListByOrganizationIdAndStatusAndType(Integer id, String status,String type);
	public void deleteMenuDayProduct(MenuDayProductEntity menuProduct);
	
	public Map<Integer,List<MenuDayProductEntity>> getListSearchMenuProduct(String searchProduct,String status);
	public List<MenuDayProductEntity> getListFavoriteMenuDayProductByUserAndOrganizationId(Integer organizationId, String status, Integer userId );

}
