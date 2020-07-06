package com.paLlevar.app.model.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.ProfileMenuOptionEntity;
@Service
public interface ProfileMenuOptionService  extends GenericCRUD<ProfileMenuOptionEntity,Integer>{

	List<ProfileMenuOptionEntity> getOptionsByProfileId(Integer profileId);
}
