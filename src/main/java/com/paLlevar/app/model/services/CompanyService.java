package com.paLlevar.app.model.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.CompanyEntity;

@Service
public interface CompanyService extends GenericCRUD<CompanyEntity, Integer>{
	List<CompanyEntity> getCompanyListByStatus(String status);
	
	CompanyEntity updateDataCompany(CompanyEntity company);
	
	
}
