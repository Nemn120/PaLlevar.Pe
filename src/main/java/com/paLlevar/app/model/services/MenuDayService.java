package com.paLlevar.app.model.services;

import com.paLlevar.app.model.entities.MenuDayEntity;

public interface MenuDayService extends GenericCRUD<MenuDayEntity, Integer>{
	public MenuDayEntity getMenuDayByDayAndOrganizationIdAndSucursalId(Integer sucursalId, Integer organizationId);
}
