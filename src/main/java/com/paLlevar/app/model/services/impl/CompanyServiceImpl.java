package com.paLlevar.app.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paLlevar.app.model.entities.CompanyEntity;
import com.paLlevar.app.model.repository.CompanyRepository;
import com.paLlevar.app.model.services.CompanyService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
	
	private static final Logger logger = LogManager.getLogger(CompanyServiceImpl.class);	

	
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
		logger.info("CompanyServiceImpl.save()");
		
		if(t.getPhoto() != null &&  t.getPhoto().length>0) {
			repo.updatePhoto(t.getId(),t.getPhoto());
		}
		if(t.getImagePanel() != null &&  t.getImagePanel().length>0) {
			repo.updateImagePanel(t.getId(),t.getImagePanel());
		}
		if(t.getId() != null) {
			CompanyEntity companyEntity= repo.findById(t.getId()).orElse(new CompanyEntity());
			BeanUtils.copyProperties(t, companyEntity);
			if(t.getAnniversaryDate() != null) {
				companyEntity.setAnniversaryDate(t.getAnniversaryDate());
			}
			return repo.save(companyEntity);
		}
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

	@Override
	public CompanyEntity updateDataCompany(CompanyEntity company) {
		CompanyEntity companyEntity= repo.getOne(company.getId());
		BeanUtils.copyProperties(company, companyEntity);
	    return repo.save(companyEntity);
	}

	@Override
	public CompanyEntity updateDirectionCompany(CompanyEntity company) {
		CompanyEntity companyEntity= repo.getOne(company.getId());
		companyEntity.setPlace(company.getPlace());
		return repo.save(companyEntity);
	}

}
