package com.paLlevar.app.model.services;

import java.util.List;

import com.paLlevar.app.model.entities.MenuOptionEntity;

public interface MenuOptionService extends GenericCRUD<MenuOptionEntity,Integer>{

	List<MenuOptionEntity> getListMenuOptionByProfileId(Integer profileId);
}
