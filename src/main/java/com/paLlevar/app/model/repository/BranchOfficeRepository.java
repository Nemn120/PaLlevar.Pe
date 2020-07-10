package com.paLlevar.app.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paLlevar.app.model.entities.BranchOfficeEntity;

public interface BranchOfficeRepository extends JpaRepository<BranchOfficeEntity, Integer>{
	@Modifying
	@Query("UPDATE BranchOfficeEntity set photo = :photo where id = :id")
	void updatePhoto(@Param("id") Integer id, @Param("photo") byte[] photo);
}
