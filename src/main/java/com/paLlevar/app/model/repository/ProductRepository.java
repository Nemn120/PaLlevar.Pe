package com.paLlevar.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paLlevar.app.model.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{
	
	//@Query("SELECT FROM PRODUCT P WHERE P.companyId =:idCompany AND/ P.sucursalId=:idSucursal")
	List<ProductEntity> findByOrganizationIdAndSucursalId(Integer organizationId,Integer sucursalId);
	List<ProductEntity> findByOrganizationId(Integer organizationId);
	
}
