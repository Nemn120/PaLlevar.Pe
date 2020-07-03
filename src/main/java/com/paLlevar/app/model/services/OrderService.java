package com.paLlevar.app.model.services;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paLlevar.app.model.entities.OrderDetailEntity;
import com.paLlevar.app.model.entities.OrderEntity;

public interface OrderService extends GenericCRUD<OrderEntity, Integer>{

	public void processOrder(OrderEntity order);
	public void saveOrderByOrganizationIdAndSucursalId(OrderEntity order);
	public List<OrderEntity> getListOrderByStatus(String status,OrderEntity order);
	public Boolean CheckOrder(Integer oid,  Integer orgId,Integer idSucursal );
}
