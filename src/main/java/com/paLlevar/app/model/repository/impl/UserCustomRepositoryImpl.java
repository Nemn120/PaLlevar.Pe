package com.paLlevar.app.model.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.paLlevar.app.model.entities.UserEntity;
import com.paLlevar.app.model.repository.UserCustomRepository;

import java.util.List;


public class UserCustomRepositoryImpl implements UserCustomRepository{
	
	@PersistenceContext
	 private EntityManager em;
	
	@Override
	public List<UserEntity> getListUserByOrganization(UserEntity user) {
		StringBuffer queryString = new StringBuffer(
		"SELECT ue From UserEntity ue where ue.organizationId=:organizationId");
		
		if(user.getStatus() != null) {
			queryString.append(" AND ue.status=:status");
		}
		if(user.getProfile() != null && user.getProfile().getIdProfile()!=null) {
			queryString.append(" AND ue.profile.idProfile=:idProfile");
		}
		if(user.getEmployeeCode()!=null) {
			queryString.append(" AND ue.employeeCode=:employeeCode");
		}
		if(user.getDocumentNumber()!=null) {
			queryString.append(" AND ue.documentNumber=:documentNumber");
		}

		Query query = em.createQuery(queryString.toString(),UserEntity.class);
		
		query.setParameter("organizationId",user.getOrganizationId());
		
		if(user.getStatus() != null) {
			query.setParameter("status",user.getStatus());
		}
		if(user.getProfile() != null && user.getProfile().getIdProfile()!=null) {
			query.setParameter("idProfile",user.getProfile().getIdProfile());
		}
		if(user.getEmployeeCode()!=null) {
			query.setParameter("employeeCode",user.getEmployeeCode());
		}
		if(user.getDocumentNumber()!=null) {
			query.setParameter("documentNumber",user.getDocumentNumber());
		}

		return query.getResultList();
		
	}

}