package com.paLlevar.app.model.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.paLlevar.app.model.entities.OrderEntity;
import com.paLlevar.app.model.repository.OrderCustomRepository;
import com.paLlevar.app.model.services.dto.SearchOrderByDeliveryManDTO;
<<<<<<< HEAD

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
=======
import com.paLlevar.app.model.services.dto.SearchOrderByFieldsDTO;

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

	@Override
	public List<OrderEntity> getOrderListByFields(SearchOrderByFieldsDTO sobf) {
		
		StringBuffer queryString = new StringBuffer(
				"SELECT o From OrderEntity o ");
		
		if(sobf.getUserOrderDocumentNumber() != null) {
			queryString.append(" INNER JOIN o.userOrder us ");
		}
		
		if(sobf.getUserAttendId() != null) {
			queryString.append(" INNER JOIN o.orderDetail ord ");
		}
		
		queryString.append(" Where o.organizationId=:organizationId");
		
		if(sobf.getStatus() != null) {
			queryString.append(" AND o.status=:status");
		}
		
		if(sobf.getUserDeliveryId() != null) {
			queryString.append(" AND o.userDeliveryId=:userDeliveryId");
		}
		
		if(sobf.getAttendDateIni()!= null && sobf.getAttendDateFin()!=null) {
			queryString.append(" AND o.attendDate BETWEEN :attendDateIni AND :attendDateFin"); //getCreateDate
		}
		
		if(sobf.getDeliveryDateIni()!= null && sobf.getDeliveryDateFin()!=null) {
			queryString.append(" AND o.deliveryDate BETWEEN :deliveryDateIni AND :deliveryDateFin"); //getCreateDate
		}
		
		if(sobf.getUserOrderDocumentNumber() != null) {
			queryString.append(" AND us.documentNumber=:userOrderDocumentNumber");
		}
		
		if(sobf.getUserAttendId() != null) {
			queryString.append(" AND ord.userAttend.id=:userAttendId");
		}
		
		
		Query query = em.createQuery(queryString.toString(), OrderEntity.class);
		
		query.setParameter("organizationId",sobf.getOrganizationId());
		
		if(sobf.getStatus() != null) {
			query.setParameter("status",sobf.getStatus());
		}
		
		if(sobf.getUserDeliveryId() != null) {
			query.setParameter("userDeliveryId",sobf.getUserDeliveryId());
		}
		
		if(sobf.getAttendDateIni()!= null && sobf.getAttendDateFin()!=null) {
			query.setParameter("attendDateIni",sobf.getAttendDateIni());
			query.setParameter("attendDateFin",sobf.getAttendDateFin());
			
		}
		
		if(sobf.getDeliveryDateIni()!= null && sobf.getDeliveryDateFin()!=null) {
			query.setParameter("deliveryDateIni",sobf.getDeliveryDateIni());
			query.setParameter("deliveryDateFin",sobf.getDeliveryDateFin());
			
		}
		
		if(sobf.getUserOrderDocumentNumber() != null) {
			query.setParameter("userOrderDocumentNumber",sobf.getUserOrderDocumentNumber());
		}
		
		if(sobf.getUserAttendId()!= null) {
			query.setParameter("userAttendId",sobf.getUserAttendId());
		}
		
		

		return query.getResultList();
	}
	
	@Override
    public List<OrderEntity>  getListOrderRecentByStatusLimitedTo(Integer limit, String status, Integer id) {
		
		StringBuffer queryString = new StringBuffer(
				"SELECT o FROM OrderEntity o WHERE o.organizationId=:organizationId AND o.status=:status ORDER BY o.createDate DESC ");
		
		Query query = em.createQuery(queryString.toString(), OrderEntity.class).setMaxResults(limit);
		query.setParameter("organizationId",id);
		query.setParameter("status",status);
		
        return query.getResultList();
    }
>>>>>>> branch 'master' of https://github.com/Nemn120/PaLlevar.Pe.git

}

