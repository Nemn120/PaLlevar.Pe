package com.paLlevar.app.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paLlevar.app.model.entities.CategoryProductEntity;

public interface CategoryProductRepository extends JpaRepository<CategoryProductEntity, Integer>{
	
	

}
