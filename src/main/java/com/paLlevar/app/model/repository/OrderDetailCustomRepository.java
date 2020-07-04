package com.paLlevar.app.model.repository;

import java.util.List;

import com.paLlevar.app.model.entities.OrderDetailEntity;

public interface OrderDetailCustomRepository {
	// metodos customizados
	
	public List<Object[]> getOrderDetailListByCriterial(OrderDetailEntity od);
}
