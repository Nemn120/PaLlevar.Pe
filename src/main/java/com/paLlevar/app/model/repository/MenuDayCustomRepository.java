package com.paLlevar.app.model.repository;

import java.util.List;

import com.paLlevar.app.model.entities.MenuDayEntity;

public interface MenuDayCustomRepository {
	List<MenuDayEntity> getMenuDayByFields(MenuDayEntity menuday);
}
