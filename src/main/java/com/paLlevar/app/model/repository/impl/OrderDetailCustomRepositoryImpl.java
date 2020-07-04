package com.paLlevar.app.model.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.paLlevar.app.model.entities.OrderDetailEntity;
import com.paLlevar.app.model.repository.OrderDetailCustomRepository;

public class OrderDetailCustomRepositoryImpl implements OrderDetailCustomRepository{

	//EntityManager em = new En
	@Override
	public List<Object[]> getOrderDetailListByCriterial(OrderDetailEntity od) {
		// TODO Auto-generated method stub
	/*	String query ="";
		if(od.getUserAttend() != null) {
			query+=" AND USER ATTEND :=" 
		}
		
		return null;
		
		*/
		return null;
	}

}
