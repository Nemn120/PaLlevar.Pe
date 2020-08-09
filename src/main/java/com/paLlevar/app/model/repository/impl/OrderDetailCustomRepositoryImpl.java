package com.paLlevar.app.model.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.paLlevar.app.model.entities.MenuDayEntity;
import com.paLlevar.app.model.entities.OrderDetailEntity;
import com.paLlevar.app.model.repository.OrderDetailCustomRepository;
import com.paLlevar.app.model.services.dto.SearchSalesByFieldsDTO;

public class OrderDetailCustomRepositoryImpl implements OrderDetailCustomRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Object[]> getOrderDetailListByCriterial(OrderDetailEntity od) {
		return null;
	}

	@Override
	public List<Object[]> getSalesByFieldsGroupByMenuProduct(SearchSalesByFieldsDTO ssbf) {
		StringBuffer queryString = new StringBuffer("SELECT pro.name as productName,cat.name,od.price,count(od) as countOd,sum(od.price) as sumOd ,od.menuProductId FROM OrderDetailEntity od "
				+ " INNER JOIN od.product pro  INNER JOIN pro.categoryProduct cat  where od.organizationId=:organizationId");
		
		if(ssbf.getCategoryId() != null) {
			queryString.append(" AND cat.id=:categoryId");
		}
		
		if(ssbf.getProductId() != null) {
			queryString.append(" AND pro.id=:productId");
		}
		if(ssbf.getInitDate()!= null && ssbf.getFinalDate()!= null) {
			queryString.append(" AND od.createDate BETWEEN :starDate AND :endDate ");
		}
		queryString.append(" group by od.menuProductId , pro.name,cat.name, od.price");
		//queryString.append(" order by count(od.id) desc ");
		
		Query query = em.createQuery(queryString.toString());
		
		query.setParameter("organizationId",ssbf.getOrganizationId());
		if(ssbf.getCategoryId() != null) {
			query.setParameter("categoryId",ssbf.getCategoryId());
		}
	
		if(ssbf.getProductId() != null) {
			query.setParameter("productId",ssbf.getProductId());
		}
		if(ssbf.getInitDate()!= null && ssbf.getFinalDate()!= null) {
			query.setParameter("starDate",ssbf.getInitDate());
			query.setParameter("endDate",ssbf.getFinalDate());
		}
		
		return query.getResultList();
	}

}
