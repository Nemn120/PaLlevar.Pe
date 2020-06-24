package com.paLlevar.app.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.ClientEntity;
import com.paLlevar.app.model.repository.ClientRepository;
import com.paLlevar.app.model.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientRepository repo;

	@Override
	public List<ClientEntity> getAll() {
		return repo.findAll();
	}

	@Override
	public ClientEntity getOneById(Integer id) {
		return repo.getOne(id);
	}

	@Override
	public ClientEntity save(ClientEntity t) {
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

}
