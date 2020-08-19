package com.paLlevar.app.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paLlevar.app.model.entities.PlaceEntity;

public interface PlaceRepository extends JpaRepository<PlaceEntity, Integer>{

}
