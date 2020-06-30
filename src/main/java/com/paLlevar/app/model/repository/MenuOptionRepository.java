package com.paLlevar.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paLlevar.app.model.entities.MenuOptionEntity;
import com.paLlevar.app.model.entities.ProfileMenuOptionEntity;

public interface MenuOptionRepository extends JpaRepository<MenuOptionEntity,Integer>{
	@Query(value="Select distinct m.*  from profile_menu_option pro  inner join  menu_option m  on pro.id_menu=m.id_menu where pro.id_profile=:profileId"
			,nativeQuery=true)
	List<Object[]> getOptionsByProfileId(@Param("profileId") Integer profileId);
	
}
