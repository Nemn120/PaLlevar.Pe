package com.paLlevar.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paLlevar.app.model.entities.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer>{
	List<CompanyEntity> findByStatus(String status);
	@Modifying
	@Query("UPDATE CompanyEntity set photo=:photo where id=:id")
	void updatePhoto(@Param("id") Integer id, @Param("photo") byte[] photo);
	
	@Modifying
	@Query("UPDATE CompanyEntity set imagePanel=:imagePanel where id=:id")
	void updateImagePanel(@Param("id") Integer id, @Param("imagePanel") byte[] photo);
}
