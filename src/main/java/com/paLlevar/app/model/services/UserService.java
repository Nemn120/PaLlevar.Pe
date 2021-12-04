package com.paLlevar.app.model.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.UserEntity;

@Service
public interface UserService extends GenericCRUD<UserEntity, Integer>{
	UserEntity getUserByUsername(String username);
	UserEntity registerUserByProfile(UserEntity user);
	List<UserEntity> getUserListByProfileANDStatus(UserEntity user);
	List<UserEntity> getUserListByOrganizationId(Integer idOrg);
	void updateStatusById(Integer id,String status);
	List<UserEntity> getUserListByOrganizationIdANDbyStatus(UserEntity user);
	Map<String,Object> validateDocument(Map<String,Object> documentRequest);
}
