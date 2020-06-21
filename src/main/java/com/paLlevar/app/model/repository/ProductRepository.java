package com.paLlevar.app.model.repository;

import org.hibernate.annotations.ParamDef;
import org.springframework.data.jpa.repository.JpaRepository;

import com.paLlevar.app.model.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	//@Query("SELECT FROM PRODUCT P WHERE P.COMPANYID =: idCompany AND P.SUCURSALID=:idSucursal")
	//List<Product> getAllProductByCompanyIdAndSucursalId(@ParamD("idCompany")Integer idCompany,Integer idSucursal);

}
