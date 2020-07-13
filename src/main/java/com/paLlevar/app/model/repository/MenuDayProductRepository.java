package com.paLlevar.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paLlevar.app.model.entities.MenuDayProductEntity;

public interface MenuDayProductRepository extends JpaRepository<MenuDayProductEntity, Integer>{

	
	@Query("SELECT m FROM MenuDayProductEntity m  WHERE m.menuDay.id=:idMenu AND m.organizationId=:organizationId AND m.sucursalId=:sucursalId")
	List<MenuDayProductEntity> getMenuDayProductByMenuIdAndOrganizationIdAndSucursalId(@Param("idMenu")Integer idMenu,@Param("organizationId") Integer organizationId, @Param("sucursalId")Integer sucursalId);
	@Query("SELECT m FROM MenuDayProductEntity m  WHERE m.menuDay.id=:idMenu AND m.organizationId=:organizationId ")
	List<MenuDayProductEntity> getMenuDayProductByMenuIdAndOrganizationId(@Param("idMenu")Integer idMenu,@Param("organizationId") Integer organizationId);
	@Query("SELECT m FROM MenuDayProductEntity m  WHERE m.menuDay.id=:idMenu") 
	List<MenuDayProductEntity> getListMenuProductByMenuId(@Param("idMenu")Integer idMenu);
	
	MenuDayProductEntity findByIdAndStatus(Integer id, String status);
	
	List<MenuDayProductEntity> findByOrganizationIdAndStatus(Integer organizationId, String status);
	
	List<MenuDayProductEntity> findByOrganizationIdAndStatusAndType(Integer organizationId, String status,String type);
	
	
}

