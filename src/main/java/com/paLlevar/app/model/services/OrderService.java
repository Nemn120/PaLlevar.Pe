package com.paLlevar.app.model.services;
import java.util.List;

import com.paLlevar.app.model.entities.OrderDetailEntity;
import com.paLlevar.app.model.entities.OrderEntity;

public interface OrderService extends GenericCRUD<OrderEntity, Integer>{

	public void processOrder(OrderEntity order);
	public void saveOrderByOrganizationIdAndSucursalId(OrderEntity order);
	public List<OrderEntity> getListOrderByStatus(String status, Integer org, Integer Integer);
}
