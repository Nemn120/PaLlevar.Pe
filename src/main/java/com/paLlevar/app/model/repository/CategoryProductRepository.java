package com.paLlevar.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paLlevar.app.model.entities.CategoryProductEntity;

public interface CategoryProductRepository extends JpaRepository<CategoryProductEntity, Integer>{
	
	
	@Modifying
	@Query("UPDATE CategoryProductEntity set photo = :photo where id = :id")
	void updatePhoto(@Param("id") Integer id, @Param("photo") byte[] photo);
	List<CategoryProductEntity> findByOrganizationId(Integer organizationId);
}
