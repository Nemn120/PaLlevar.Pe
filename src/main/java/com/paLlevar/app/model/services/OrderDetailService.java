package com.paLlevar.app.model.services;
import java.util.List;

import com.paLlevar.app.model.entities.OrderDetailEntity;
import com.paLlevar.app.model.entities.OrderEntity;

public interface OrderDetailService extends GenericCRUD<OrderDetailEntity, Integer>{
	public void saveAttendOrderDetail(OrderDetailEntity order);

	public OrderDetailEntity getOrderDetailByStatusAndId(String status,Integer id, Integer organization, Integer sucursal);

	public List<OrderDetailEntity> getListOrderDetailByStatus(String status, Integer org, Integer suc);
	
}
