package com.paLlevar.app.model.services;

import java.util.List;

import com.paLlevar.app.model.entities.UserEntity;

public interface UserService extends GenericCRUD<UserEntity, Integer>{
	UserEntity getUserByUsername(String username);
	UserEntity registerUserByProfile(UserEntity user);
	List<UserEntity> getUserListByProfileANDStatus(UserEntity user);
}
