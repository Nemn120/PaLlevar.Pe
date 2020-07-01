package com.paLlevar.app.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paLlevar.app.model.entities.ProfileEntity;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer>{
	

}