package com.paLlevar.app.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.CompanyEntity;
import com.paLlevar.app.model.repository.CompanyRepository;
import com.paLlevar.app.model.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyRepository repo;

	@Override
	public List<CompanyEntity> getAll() {
		return repo.findAll();
	}

	@Override
	public CompanyEntity getOneById(Integer id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public CompanyEntity save(CompanyEntity t) {
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public List<CompanyEntity> getCompanyListByStatus(String status) {
		return repo.findByStatus(status);
	}

}
