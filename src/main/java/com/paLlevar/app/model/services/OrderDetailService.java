package com.paLlevar.app.model.services;
import com.paLlevar.app.model.entities.OrderDetailEntity;
import com.paLlevar.app.model.entities.OrderEntity;

public interface OrderDetailService extends GenericCRUD<OrderDetailEntity, Integer>{
	public void saveAttendOrderDetail(OrderDetailEntity order);
}
