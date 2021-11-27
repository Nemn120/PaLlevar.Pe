package com.paLlevar.app.model.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.ProductEntity;

@Service
public interface ProductService extends GenericCRUD<ProductEntity,Integer>{
	
	List<ProductEntity> getAllProductByCompanyIdAndSucursalId(ProductEntity pro);
	List<ProductEntity> getAllProductByCompanyId(Integer companyId );

}
