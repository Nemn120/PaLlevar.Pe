package com.paLlevar.app.model.services;

import java.util.List;

import com.paLlevar.app.model.entities.Product;

public interface ProductService extends GenericCRUD<Product,Integer>{
	
	List<Product> getAllProductByCompanyIdAndSucursalId(Integer companyId,Integer sucursalId);

}
