package com.paLlevar.app.model.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		StringBuffer queryString = new StringBuffer(
				"SELECT  From Ormd where md.organizationId=:organizationId");
		return null;
	}

}
