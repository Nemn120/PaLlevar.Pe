package com.paLlevar.app.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.CategoryProductEntity;
import com.paLlevar.app.model.repository.CategoryProductRepository;
import com.paLlevar.app.model.services.CategoryProductService;

@Service
public class CategoryProductServiceImpl implements CategoryProductService {

	@Autowired
	private CategoryProductRepository repo;
	
	@Override
	public List<CategoryProductEntity> getAll() {
		return repo.findAll();
	}
	@Override
	public CategoryProductEntity getOneById(Integer id) {
		return repo.getOne(id);
	}

	@Override
	public CategoryProductEntity save(CategoryProductEntity t) {
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

}
