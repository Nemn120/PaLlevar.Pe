package com.paLlevar.app.model.services;

import java.util.List;

import com.paLlevar.app.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.UserEntity;

@Service
public interface UserService extends GenericCRUD<UserDTO, Integer>{
	UserDTO getUserByUsername(String username);
	UserDTO registerUserByProfile(UserEntity user);
	List<UserDTO> getUserListByProfileANDStatus(UserEntity user);
	List<UserDTO> getUserListByOrganizationId(Integer idOrg);
	void updateStatusById(Integer id,String status);
	List<UserDTO> getUserListByOrganizationIdANDbyStatus(UserEntity user);
}
