package com.paLlevar.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paLlevar.app.model.entities.MenuDayEntity;

public interface MenuDayRepository extends MenuDayCustomRepository ,  JpaRepository<MenuDayEntity, Integer>{

	List<MenuDayEntity> findByStatusAndOrganizationIdAndSucursalId(String status, Integer organizationId, Integer sucursalId);
	List<MenuDayEntity> findByStatusAndOrganizationId(String status, Integer organizationId);
	List<MenuDayEntity> findByOrganizationId(Integer organizationId);
	@Modifying
	@Query("UPDATE MenuDayEntity set status=:status where id=:id and organizationId=:orgId")
	public void updateStatus(@Param("id") Integer id, @Param("status") String status, @Param("orgId") Integer orgId);
	
	@Query("SELECT m FROM MenuDayEntity m WHERE m.organizationId=:organizationId AND m.status=:status AND m.day=:day")
	public List<MenuDayEntity> getListMenuDayByDay(@Param("organizationId") Integer organizationId, @Param("status") String status, 
			@Param("day") String day);
	
	
}


