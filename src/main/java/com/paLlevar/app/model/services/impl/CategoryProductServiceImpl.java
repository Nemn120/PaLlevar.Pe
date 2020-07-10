package com.paLlevar.app.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paLlevar.app.model.entities.CategoryProductEntity;
import com.paLlevar.app.model.entities.ProductEntity;
import com.paLlevar.app.model.repository.CategoryProductRepository;
import com.paLlevar.app.model.services.CategoryProductService;

@Service
@Transactional
public class CategoryProductServiceImpl implements CategoryProductService {

	@Autowired
	private CategoryProductRepository repo;
	
	@Override
	public List<CategoryProductEntity> getAll() {
		return repo.findAll();
	}
	@Override
	public CategoryProductEntity getOneById(Integer id) {
		return repo.findById(id).orElse(new CategoryProductEntity());
	}

	@Override
	public CategoryProductEntity save(CategoryProductEntity t) {
		if(t.getId() != null && t.getPhoto().length>0) {
			repo.updatePhoto(t.getId(), t.getPhoto());
		}
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
	@Override
	public List<CategoryProductEntity> getListCategoryProductByOrgId(Integer organizationId) {
		return repo.findByOrganizationId(organizationId);
	}

}
