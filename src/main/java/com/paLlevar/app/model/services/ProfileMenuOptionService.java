package com.paLlevar.app.model.services;

import java.util.List;


import com.paLlevar.app.model.entities.ProfileMenuOptionEntity;

public interface ProfileMenuOptionService  extends GenericCRUD<ProfileMenuOptionEntity,Integer>{

	List<ProfileMenuOptionEntity> getOptionsByProfileId(Integer profileId);
}
