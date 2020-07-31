package com.paLlevar.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paLlevar.app.model.entities.ProfileEntity;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer>{
	
	//List<ProfileEntity>findByIdProfileNot(Integer idProfile);
	List<ProfileEntity>findByNameNotIn(List<String> listProfileName);
}
