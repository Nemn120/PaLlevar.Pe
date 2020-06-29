package com.paLlevar.app.model.services;

import com.paLlevar.app.model.entities.UserEntity;

public interface UserService extends GenericCRUD<UserEntity, Integer>{
	UserEntity getUserByUsername(String username);
	
	
	UserEntity registerUserByProfile(UserEntity user);
}
