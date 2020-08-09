package com.paLlevar.app.model.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.paLlevar.app.model.entities.OrderEntity;
import com.paLlevar.app.model.repository.OrderCustomRepository;
import com.paLlevar.app.model.services.dto.SearchOrderByDeliveryManDTO;

public class OrderCustomRepositoryImpl implements OrderCustomRepository{


	 @PersistenceContext
	 private EntityManager em;
	 
	@Override
	public List<OrderEntity> getOrderListByDeliveryId(SearchOrderByDeliveryManDTO sobd) {
		StringBuffer queryString = new StringBuffer(
				"SELECT o From OrderEntity o where o.userDeliveryId=:userDeliveryId");
		
		if(sobd.getStatus() != null) {
			queryString.append(" AND o.status=:status");
		}
		
		if(sobd.getInitDate()!= null && sobd.getFinalDate()!=null) {
			queryString.append(" AND o.createDate BETWEEN :initDate AND :finalDate"); //getCreateDate
		}
		
		Query query = em.createQuery(queryString.toString(), OrderEntity.class);
		
		query.setParameter("userDeliveryId",sobd.getDeliveryId());
		
		if(sobd.getStatus() != null) {
			query.setParameter("status",sobd.getStatus());
		}
		
		if(sobd.getInitDate()!= null && sobd.getFinalDate()!=null) {
			query.setParameter("initDate",sobd.getInitDate());
			query.setParameter("finalDate",sobd.getFinalDate());
			
		}
		
		return query.getResultList();
		//return null;
	}

}

