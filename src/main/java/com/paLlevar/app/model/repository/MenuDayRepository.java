package com.paLlevar.app.model.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paLlevar.app.model.entities.MenuDayEntity;

public interface MenuDayRepository extends JpaRepository<MenuDayEntity, Integer>{

	//@Query("SELECT FROM MenuDayEntity md WHERE md.date=:day and md.sucursalId=:sucursalId AND md.organizationId=:organizationId ")
	//public MenuDayEntity getMenuDayByDayAn(@Param("day") Date day, @Param("sucursalId") Integer idSucursal, @Param("organizationId") Integer orgId );
}
