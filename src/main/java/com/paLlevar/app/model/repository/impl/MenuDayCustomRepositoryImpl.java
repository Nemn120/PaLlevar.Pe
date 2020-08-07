package com.paLlevar.app.model.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.paLlevar.app.model.entities.MenuDayEntity;
import com.paLlevar.app.model.repository.MenuDayCustomRepository;

public class MenuDayCustomRepositoryImpl implements MenuDayCustomRepository {

	 @PersistenceContext
	 private EntityManager em;

	@Override
	public List<MenuDayEntity> getMenuDayByFields(MenuDayEntity menuday) {
		StringBuffer queryString = new StringBuffer(
				"SELECT md From MenuDayEntity md where md.organizationId=:organizationId");
		
		if(menuday.getSucursalId() != null) {
			queryString.append(" AND md.sucursalId=:sucursalId");
		}
		if(menuday.getDay() != null) {
			queryString.append(" AND md.day=:day");
		}
		if(menuday.getStatus() != null) {
			queryString.append(" AND md.status=:status");
		}
		if(menuday.getType()!= null) {
			queryString.append(" AND md.type=:type");
		}
		if(menuday.getName() != null) {
			queryString.append(" AND md.name LIKE :name");
		}
		if(menuday.getDescription()!= null) {
			queryString.append(" AND md.description LIKE :description");
		}
		if(menuday.getLocalDateTime()!= null && menuday.getLocalDateTimeFinal()!=null) {
			queryString.append(" AND md.getLocalDateTime BETWEEN :starDate AND :endDate");
		}
		Query query = em.createQuery(queryString.toString(), MenuDayEntity.class);
		
		query.setParameter("organizationId",menuday.getOrganizationId());
		
		if(menuday.getSucursalId() != null) {
			query.setParameter("sucursalId",menuday.getSucursalId());
		}
		if(menuday.getDay() != null) {
			query.setParameter("day",menuday.getDay());
		}
		if(menuday.getStatus() != null) {
			query.setParameter("status",menuday.getStatus());
		}
		if(menuday.getType()!= null) {
			query.setParameter("type",menuday.getType());
		}
		if(menuday.getName() != null) {
			query.setParameter("name","%"+menuday.getName()+"%");
		}
		if(menuday.getDescription()!= null) {
			query.setParameter("description","%"+menuday.getDescription()+"%");
		}
		if(menuday.getLocalDateTime()!= null && menuday.getLocalDateTimeFinal()!=null) {
			query.setParameter("starDate",menuday.getLocalDateTime());
			query.setParameter("endDate",menuday.getLocalDateTimeFinal());
			
		}
		
		return query.getResultList();
		//return null;
	
	}
	
	
}
