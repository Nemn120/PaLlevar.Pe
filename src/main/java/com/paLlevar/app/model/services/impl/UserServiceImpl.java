package com.paLlevar.app.model.services.impl;

import java.util.List;

import com.paLlevar.app.model.dto.UserDTO;
import com.paLlevar.app.model.services.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paLlevar.app.model.entities.UserEntity;
import com.paLlevar.app.model.repository.UserRepository;
import com.paLlevar.app.model.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PasswordEncoder  bcrypt;

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<UserDTO> getAll() {
		return userMapper.mapOut(repo.findAll());
	}

	@Override
	public UserDTO getOneById(Integer id) {
		return userMapper.mapOut(repo.findById(id).orElse(new UserEntity()));
	}

	@Override
	public UserDTO save(UserDTO t) {
		if(t.getPhoto() != null &&  t.getPhoto().length>0) {
			repo.updatePhoto(t.getId(),t.getPhoto());
		}
		return userMapper.mapOut(repo.save(userMapper.mapIn(t)));
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public UserDTO registerUserByProfile(UserEntity user) {
		user.setPassword(bcrypt.encode(user.getPassword()));
		return userMapper.mapOut(repo.save(user));
	}

	@Override
	public UserDTO getUserByUsername(String username) {
		return userMapper.mapOut(repo.findOneByUsername(username));
	}

	@Override
	public List<UserDTO> getUserListByProfileANDStatus(UserEntity user) {
		return userMapper.mapOut(repo.getUserListByStatusAndProfileID(user.getStatus(),user.getOrganizationId(),user.getProfile().getIdProfile()));
	}

	@Override
	public List<UserDTO> getUserListByOrganizationId(Integer idOrg) {
		return userMapper.mapOut(repo.findByOrganizationId(idOrg));
	}

	@Override
	public void updateStatusById(Integer id, String status) {
		repo.updateStatusById(id, status);
		
	}

	@Override
	public List<UserDTO> getUserListByOrganizationIdANDbyStatus(UserEntity user) {
		return userMapper.mapOut(repo.getListUserByOrganization(user));
	}


}
