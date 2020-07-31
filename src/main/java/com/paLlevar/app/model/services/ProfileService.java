package com.paLlevar.app.model.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.ProfileEntity;
@Service
public interface ProfileService extends GenericCRUD<ProfileEntity, Integer>{
	List<ProfileEntity> getProfileListNotIdProfile(List<String> listProfile);
}
