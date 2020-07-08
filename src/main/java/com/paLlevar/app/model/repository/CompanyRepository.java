package com.paLlevar.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paLlevar.app.model.entities.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer>{
	List<CompanyEntity> findByStatus(String status);
}
