package com.paLlevar.app.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paLlevar.app.model.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{
	
	//@Query("SELECT FROM PRODUCT P WHERE P.COMPANYID =: idCompany AND P.SUCURSALID=:idSucursal")
	//List<Product> getAllProductByCompanyIdAndSucursalId(@ParamD("idCompany")Integer idCompany,Integer idSucursal);

}
