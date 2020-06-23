package com.paLlevar.app.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.ProductEntity;
import com.paLlevar.app.model.repository.ProductRepository;
import com.paLlevar.app.model.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository repo;

	@Autowired
	//private UserService userService;
	
	@Override
	public List<ProductEntity> getAll() {
		return repo.findAll();
	}

	@Override
	public ProductEntity getOneById(Integer id) {
		return repo.getOne(id);
	}

	@Override
	public ProductEntity save(ProductEntity t) {
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public List<ProductEntity> getAllProductByCompanyIdAndSucursalId(Integer companyId, Integer sucursalId) {
		//List<Product> p = repo.getAllProductByCompanyIdAndSucursalId(companyId,sucursalId);
		//List<User> u =  userService.getUserByCriterial(Criterio criterio);
		return null;
		
	}
	
}
