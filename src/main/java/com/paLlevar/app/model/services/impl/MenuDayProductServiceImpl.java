package com.paLlevar.app.model.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.MenuDayEntity;
import com.paLlevar.app.model.entities.MenuDayProductEntity;
import com.paLlevar.app.model.repository.MenuDayProductRepository;
import com.paLlevar.app.model.services.MenuDayProductService;

@Service
public class MenuDayProductServiceImpl implements MenuDayProductService {

	@Autowired
	private MenuDayProductRepository repo;

	@Override
	public List<MenuDayProductEntity> getAll() {
		return repo.findAll();
	}

	@Override
	public MenuDayProductEntity getOneById(Integer id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public MenuDayProductEntity save(MenuDayProductEntity t) {
		if (t.getId() == null) {
			t.setCreateDate(LocalDateTime.now());
			if (t.getQuantity() != null)
				t.setAvailable(t.getQuantity());
			if (t.getMenuDayId() != null) {
				t.setMenuDay(new MenuDayEntity());
				t.getMenuDay().setId(t.getMenuDayId());
			}
		}
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public MenuDayProductEntity saveMenuDayProduct(MenuDayProductEntity menuDayProduct) {
		menuDayProduct.setCreateDate(LocalDateTime.now());
		if (menuDayProduct.getQuantity() != null)
			menuDayProduct.setAvailable(menuDayProduct.getQuantity());

		return repo.save(menuDayProduct);

	}

	@Override
	public List<MenuDayProductEntity> getMenuDayProductListByMenuIdAndSucursalIdAndOrganizationId(
			MenuDayProductEntity menuDayProduct) {
		return repo.getMenuDayProductByMenuIdAndOrganizationIdAndSucursalId(menuDayProduct.getMenuDayId(),
				menuDayProduct.getOrganizationId(), menuDayProduct.getSucursalId());
	}

	@Override
	public List<MenuDayProductEntity> getMenuDayProductListByMenuIdAndOrganizationId(
			MenuDayProductEntity menuDayProduct) {
		return repo.getMenuDayProductByMenuIdAndOrganizationId(menuDayProduct.getMenuDayId(),
				menuDayProduct.getOrganizationId());
	}

	@Override
	public List<MenuDayProductEntity> getListMenuProductByMenuId(Integer idMenu) {

		return repo.getListMenuProductByMenuId(idMenu);
	}

	@Override
	public MenuDayProductEntity getMenuByIdAndStatus(Integer id, String status) {

		return repo.findByIdAndStatus(id, status);
	}

}
