package com.paLlevar.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paLlevar.app.model.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{

	List<ProductEntity> findByOrganizationIdAndSucursalId(Integer organizationId,Integer sucursalId);
	List<ProductEntity> findByOrganizationId(Integer organizationId);
	List<ProductEntity> findByCategoryProductId(Integer categoryId);
	@Modifying
	@Query("UPDATE ProductEntity set photo=:photo where id=:id")
	void updatePhoto(@Param("id") Integer id, @Param("photo") byte[] photo);
}
