package com.paLlevar.app.model.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.paLlevar.app.model.entities.MenuDayEntity;
import com.paLlevar.app.model.entities.MenuDayProductEntity;
import com.paLlevar.app.model.entities.ProductEntity;
import com.paLlevar.app.model.repository.MenuDayProductRepository;
import com.paLlevar.app.model.services.MenuDayProductService;
import com.paLlevar.app.util.Constants;

@Service
@Transactional
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
		MenuDayProductEntity md =repo.findById(id).orElse(new MenuDayProductEntity());
		md.setProduct(new ProductEntity());
		md.setMenuDay(new MenuDayEntity());
		repo.save(md);
		repo.deleteById(id);
	}

	@Override
	public MenuDayProductEntity saveEditMenuDayProduct(MenuDayProductEntity menuDayProduct) {
		if(menuDayProduct.getQuantityAdd() != null && menuDayProduct.getQuantityAdd() != 0) {
			menuDayProduct.setQuantity(menuDayProduct.getQuantity()+ menuDayProduct.getQuantityAdd());
			menuDayProduct.setAvailable(menuDayProduct.getAvailable()+ menuDayProduct.getQuantityAdd());
		}
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

	@Override
	public List<MenuDayProductEntity> getListByOrganizationIdAndStatus(Integer id, String status) {
		return repo.findByOrganizationIdAndStatus(id,status);
	}
	
	@Override
	public List<MenuDayProductEntity> getListByOrganizationIdAndStatusAndCategory(Integer id, String status, Integer categoryId ) {
		return repo.getMenuDayProductByOrganizationAndStatusAndCategory(id,status,categoryId);
	}

	@Override
	public List<MenuDayProductEntity> getListByOrganizationIdAndStatusAndType(Integer id, String status, String type) {
		return repo.findByOrganizationIdAndStatusAndType(id, status, type);
	}

	@Override
	public void deleteMenuDayProduct(MenuDayProductEntity menuProduct) {
		menuProduct.setMenuDay(new MenuDayEntity());
		menuProduct.getMenuDay().setId(menuProduct.getMenuDayId()); //
		menuProduct.setStatus(Constants.STATUS_OFF_ENTITY);
		repo.save(menuProduct);
	}

	@Override
	public Map<Integer,List<MenuDayProductEntity>> getListSearchMenuProduct(String searchProduct, String status) {
		 List<MenuDayProductEntity> menuSearch = repo.getListSearchMenuProduct(searchProduct, status);
		 Map<Integer,List<MenuDayProductEntity>> mapSearch= new HashMap<Integer,List<MenuDayProductEntity>>();
		 
		 menuSearch.forEach(menuProduct ->{
			 if(mapSearch.containsKey(menuProduct.getOrganizationId())){
				 mapSearch.get(menuProduct.getOrganizationId()).add(menuProduct);
			 }else {
				 List<MenuDayProductEntity> menuProductList = new ArrayList<>();
				 menuProductList .add(menuProduct);
				 mapSearch.put(menuProduct.getOrganizationId(), menuProductList);
			 }
		 });
		 return mapSearch;
	}
	
	public List<MenuDayProductEntity> getListFavoriteMenuDayProductByUserAndOrganizationId(Integer organizationId, String status,
			Integer userId) {
		List<MenuDayProductEntity> lis= repo.getListFavoriteMenuDayProductByUserAndOrganizationId(organizationId, status, userId);
		List<MenuDayProductEntity> lis2 = deleteDuplicated(lis);
		lis2.sort(Comparator.comparing(MenuDayProductEntity::getCreateDate).reversed());
		List<MenuDayProductEntity> lis3 =new ArrayList<>();
		int i=0;
		for(MenuDayProductEntity me : lis2) {
			if(me !=null && i<5)
				lis3.add(me);
			i++;
		}
		
		return lis3;
		
	}
	
	private List<MenuDayProductEntity> deleteDuplicated(List<MenuDayProductEntity> lis){
		Set<MenuDayProductEntity> noRepeat = new HashSet<MenuDayProductEntity>();
		noRepeat.addAll(lis);
		return new ArrayList<MenuDayProductEntity>(noRepeat);
	}

}
