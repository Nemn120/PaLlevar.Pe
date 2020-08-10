package com.paLlevar.app.model.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.MenuDayEntity;
import com.paLlevar.app.model.entities.MenuDayProductEntity;
import com.paLlevar.app.model.repository.MenuDayRepository;
import com.paLlevar.app.model.services.MenuDayProductService;
import com.paLlevar.app.model.services.MenuDayService;
import com.paLlevar.app.util.Constants;

@Service
@Transactional
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
		return repo.findById(id).orElse(null);
	}


	@Override
	public MenuDayEntity saveMenuDay(MenuDayEntity t) {
		repo.save(t);
		if(t.getMenuDayProductList() != null) {
			t.getMenuDayProductList().forEach(menuDayProduct ->{
				menuDayProduct.setStatus(Constants.MENUD_PROD_STATUS_AVAILABLE);
				menuDayProduct.setMenuDay(new MenuDayEntity());
				menuDayProduct.getMenuDay().setId(t.getId());
				menuDayProduct.setMenuDayId(t.getId());
			
				if(t.getTotal() !=null && t.getTotal() != 0.0)
					t.setTotal(t.getTotal()+menuDayProduct.getPrice());
				else
					t.setTotal(menuDayProduct.getPrice());
				if(t.getCountTotal() !=null)
					t.setCountTotal(t.getCountTotal()+menuDayProduct.getQuantity());
				else
					t.setCountTotal(menuDayProduct.getQuantity());
				menuDayProduct.setOrganizationId(t.getOrganizationId());
				menuDayProduct.setType(t.getType());
				if(t.getUserCreateId() != null)
					menuDayProduct.setUserCreateId(t.getUserCreateId());
				
				menuDayProductService.save(menuDayProduct);
			});
		}
		t.setLocalDateTime(LocalDateTime.now());
		return repo.save(t);
	}
	
	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
	
	@Override
	public List<MenuDayEntity> getMenuDayListByFields(MenuDayEntity menuDay) {
		List<MenuDayEntity> aux =  repo.getMenuDayByFields(menuDay);
		return this.copyMenuDay(aux);
	}

	@Override
	public MenuDayEntity editMenuDay(MenuDayEntity menuDay) {
		return repo.save(menuDay);
	}

	@Override
	public MenuDayEntity save(MenuDayEntity t) {
		return repo.save(t);
	}

	@Override
	public List<MenuDayEntity> getMenuDayListByStatusAndOrgAndSuc(MenuDayEntity md) {
		List<MenuDayEntity> aux = repo.findByStatusAndOrganizationIdAndSucursalId(md.getStatus(), md.getOrganizationId(),md.getSucursalId());
		return this.copyMenuDay(aux);
	}

	@Override
	public List<MenuDayEntity> getMenuDayListByStatusAndOrg(MenuDayEntity md) {
		List<MenuDayEntity> aux = repo.findByStatusAndOrganizationId(md.getStatus(), md.getOrganizationId());
		return this.copyMenuDay(aux);
	}

	@Override
	public boolean deleteMenuDayAndProductDayList(Integer menuDayId) {
		List<MenuDayProductEntity> mdpList = menuDayProductService.getListMenuProductByMenuId(menuDayId);
		try {
		if(mdpList != null) {
			mdpList.forEach(x ->{
			menuDayProductService.deleteById(x.getId());
			});
			
		}
		repo.deleteById(menuDayId);
		return true;
		}catch(Exception e) {
			return false;
		}
	}
	private List<MenuDayEntity> copyMenuDay(List<MenuDayEntity> md) {
		md.forEach(x -> {
			x.getMenuDayProductList().forEach(data -> data.setMenuDayId(data.getMenuDay().getId()));
		});
		return md;
	}

	@Override
	public List<MenuDayEntity> getMenuDayListByOrg(Integer orgId) {
		List<MenuDayEntity> aux = repo.findByOrganizationId(orgId);
		return this.copyMenuDay(aux);
	}


}

