package com.paLlevar.app.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paLlevar.app.model.entities.CategoryProductEntity;
import com.paLlevar.app.model.entities.ProductEntity;
import com.paLlevar.app.model.repository.ProductRepository;
import com.paLlevar.app.model.services.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository repo;
	
	@Override
	public List<ProductEntity> getAll() {
		return repo.findAll();
	}

	@Override
	public ProductEntity getOneById(Integer id) {
		return repo.findById(id).orElse(new ProductEntity());
	}

	@Override
	public ProductEntity save(ProductEntity t) {
		if(t.getPhoto() != null &&  t.getPhoto().length>0) {
			repo.updatePhoto(t.getId(),t.getPhoto());
		}
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		ProductEntity pro= repo.findById(id).orElse(null);
		if( pro != null && pro.getCategoryProduct() != null) {
			pro.setCategoryProduct(null);
			repo.save(pro);
		}
		repo.deleteById(id);
	}

	@Override
	public List<ProductEntity> getAllProductByCompanyIdAndSucursalId(ProductEntity pro) {
		return  repo.findByOrganizationIdAndSucursalId(pro.getOrganizationId(),pro.getSucursalId());
	}

	@Override
	public List<ProductEntity> getAllProductByCompanyId(Integer companyId) {
		return repo.findByOrganizationId(companyId);
	}

	@Override
	public List<ProductEntity> findByCategoryProductId(Integer categoryId) {
		return repo.findByCategoryProductId(categoryId);
	}
}
