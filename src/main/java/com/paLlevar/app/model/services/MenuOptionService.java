package com.paLlevar.app.model.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.MenuOptionEntity;
@Service
public interface MenuOptionService extends GenericCRUD<MenuOptionEntity,Integer>{

	List<MenuOptionEntity> getListMenuOptionByProfileId(Integer profileId);
}
