package com.paLlevar.app.model.services;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.OrderDetailEntity;
import com.paLlevar.app.model.entities.OrderEntity;

@Service
public interface OrderDetailService extends GenericCRUD<OrderDetailEntity, Integer>{
	public void saveAttendOrderDetail(OrderDetailEntity order);

	public OrderDetailEntity getOrderDetailByStatusAndId(String status,Integer id, Integer organization, Integer sucursal);

	public List<OrderDetailEntity> getListOrderDetailByStatus(String status, Integer org, Integer suc);
	
	/*public void assignDeliveryMan(Integer idOrder, Integer idDeliveryMan, Integer idSuc, Integer idOrg);*/
	
	public List<OrderDetailEntity> getListOrderDetailByOrderId(Integer oid, Integer orgId, Integer sucursalId );
	
}
