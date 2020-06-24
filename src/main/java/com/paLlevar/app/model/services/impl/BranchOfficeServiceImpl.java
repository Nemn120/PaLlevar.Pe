package com.paLlevar.app.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.BranchOfficeEntity;
import com.paLlevar.app.model.repository.BranchOfficeRepository;
import com.paLlevar.app.model.services.BranchOfficeService;

@Service
public class BranchOfficeServiceImpl implements BranchOfficeService {
	
	@Autowired
	private BranchOfficeRepository repo;

	@Override
	public List<BranchOfficeEntity> getAll() {	
		return repo.findAll();
	}

	@Override
	public BranchOfficeEntity getOneById(Integer id) {
		return repo.getOne(id);
	}

	@Override
	public BranchOfficeEntity save(BranchOfficeEntity t) {
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

}
