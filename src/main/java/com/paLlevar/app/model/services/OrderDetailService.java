package com.paLlevar.app.model.services;
import java.util.List;


import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.OrderDetailEntity;

@Service
public interface OrderDetailService extends GenericCRUD<OrderDetailEntity, Integer>{
	public void saveAttendOrderDetail(OrderDetailEntity order);

	//public OrderDetailEntity getOrderDetailByStatusAndId(String status,Integer id, Integer organization, Integer sucursal);

	public OrderDetailEntity getOrderDetailByStatusAndId(String status,Integer id, Integer organization);

	//public List<OrderDetailEntity> getListOrderDetailByStatus(String status, Integer org, Integer suc);
	
	public List<OrderDetailEntity> getListOrderDetailByStatus(String status, Integer org);
	
	/*public void assignDeliveryMan(Integer idOrder, Integer idDeliveryMan, Integer idSuc, Integer idOrg);*/
	
	//public List<OrderDetailEntity> getListOrderDetailByOrderId(Integer oid, Integer orgId, Integer sucursalId );
	
	public List<OrderDetailEntity> getListOrderDetailByOrderId(Integer oid, Integer orgId);
	
	public boolean updateOrderDetailStatus( Integer id, String status);
	
}
