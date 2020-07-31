package com.paLlevar.app.model.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.UserEntity;

@Service
public interface UserService extends GenericCRUD<UserEntity, Integer>{
	UserEntity getUserByUsername(String username);
	UserEntity registerUserByProfile(UserEntity user);
	List<UserEntity> getUserListByProfileANDStatus(UserEntity user);
	List<UserEntity> getUserListByOrganizationId(Integer idOrg);
}
