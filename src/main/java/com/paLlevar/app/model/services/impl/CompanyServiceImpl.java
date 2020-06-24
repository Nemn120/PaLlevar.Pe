package com.paLlevar.app.model.services.impl;

import java.util.List;

import com.paLlevar.app.model.entities.CompanyEntity;
import com.paLlevar.app.model.repository.CompanyRepository;
import com.paLlevar.app.model.services.CompanyService;

public class CompanyServiceImpl implements CompanyService {
	
	private CompanyRepository repo;

	@Override
	public List<CompanyEntity> getAll() {
		return repo.findAll();
	}

	@Override
	public CompanyEntity getOneById(Integer id) {
		return repo.getOne(id);
	}

	@Override
	public CompanyEntity save(CompanyEntity t) {
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

}
