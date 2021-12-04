package com.paLlevar.app.model.services.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.paLlevar.app.config.RestTemplateClient;
import com.paLlevar.app.model.services.dto.DocumentApiResponseDTO;
import com.paLlevar.app.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paLlevar.app.model.entities.UserEntity;
import com.paLlevar.app.model.repository.UserRepository;
import com.paLlevar.app.model.services.UserService;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PasswordEncoder  bcrypt;

	@Autowired
	private RestTemplateClient rtc;

	@Override
	public List<UserEntity> getAll() {
		return repo.findAll();
	}

	@Override
	public UserEntity getOneById(Integer id) {
		return repo.findById(id).orElse(new UserEntity());
	}

	@Override
	public UserEntity save(UserEntity t) {
		if(t.getPhoto() != null &&  t.getPhoto().length>0) {
			repo.updatePhoto(t.getId(),t.getPhoto());
		}
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public UserEntity registerUserByProfile(UserEntity user) {
		user.setPassword(bcrypt.encode(user.getPassword()));
		return repo.save(user);
	}

	@Override
	public UserEntity getUserByUsername(String username) {
		return repo.findOneByUsername(username);
	}

	@Override
	public List<UserEntity> getUserListByProfileANDStatus(UserEntity user) {
		return repo.getUserListByStatusAndProfileID(user.getStatus(),user.getOrganizationId(),user.getProfile().getIdProfile());
	}

	@Override
	public List<UserEntity> getUserListByOrganizationId(Integer idOrg) {
		return repo.findByOrganizationId(idOrg);
	}

	@Override
	public void updateStatusById(Integer id, String status) {
		repo.updateStatusById(id, status);
		
	}

	@Override
	public List<UserEntity> getUserListByOrganizationIdANDbyStatus(UserEntity user) {
		return repo.getListUserByOrganization(user);
	}

	@Override
	public Map<String,Object> validateDocument(Map<String,Object> documentRequest){
		String document = documentRequest.get("documento").toString();
		String typeDocument = documentRequest.get("tipo_documento").toString();
		Map<String,Object> documentData = new HashMap<>();
		DocumentApiResponseDTO responseApi = documentIsValid(document, typeDocument);
		if(responseApi.isValid()){
			documentData.put("documentData", responseApi.getData());
			documentData.put("error", false);
			documentData.put("message", Constants.DOCUMENT_FOUND);
		}else{
			documentData.put("error", true);
			documentData.put("message", Constants.DOCUMENT_NOT_VALID);
		}
		return documentData;
	}

	private DocumentApiResponseDTO documentIsValid(String document, String typeDocument){
		DocumentApiResponseDTO responseApi = new DocumentApiResponseDTO();
		URI urlAPIDocument = UriComponentsBuilder
				.fromUriString(Constants.API_DOCUMENT_URL + typeDocument)
				.queryParam("numero", document).build().toUri();
		try {
			ResponseEntity<Map> documentDataResponse = rtc.restTemplate().getForEntity(urlAPIDocument, Map.class);
			if(documentDataResponse.getStatusCodeValue() == 200){
				responseApi.setValid(true);
				responseApi.setData(documentDataResponse.getBody());
			}
		} catch (Exception ex) {
			log.error("El documento:"+document+ " no es valido");
		}
		return responseApi;
	}


	
	
	

}
