package com.paLlevar.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paLlevar.app.model.entities.MenuDayEntity;

public interface MenuDayRepository extends MenuDayCustomRepository ,  JpaRepository<MenuDayEntity, Integer>{

	
}

