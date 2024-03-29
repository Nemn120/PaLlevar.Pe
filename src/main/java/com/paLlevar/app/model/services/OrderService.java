package com.paLlevar.app.model.services;
import java.util.List;


import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.OrderEntity;
import com.paLlevar.app.model.services.dto.DashBoardDTO;
import com.paLlevar.app.model.services.dto.SearchOrderByDeliveryManDTO;
import com.paLlevar.app.model.services.dto.SearchOrderByFieldsDTO;

@Service
public interface OrderService extends GenericCRUD<OrderEntity, Integer>{

	public void processOrder(OrderEntity order);
	public OrderEntity saveOrderByOrganizationIdAndSucursalId(OrderEntity order);
	public List<OrderEntity> saveOrderByManyOrganization(OrderEntity order);
	
	public List<OrderEntity> getListOrderByStatus(String status,OrderEntity order);
	public List<OrderEntity> getListOrderAttend(String status, OrderEntity order);
	public Boolean CheckOrder(Integer oid,  Integer orgId);
	public List<OrderEntity> getListOrderByNotStatusAndUserId(List<String> status, Integer userId);
	public List<OrderEntity> getListOrderStatusAndOrgId(List<String> status, Integer orgId);
	public boolean deliveryOrder(OrderEntity order);
	public void attendOrder(OrderEntity order);
	
	public List<OrderEntity> getOrderListByDeliveryId(SearchOrderByDeliveryManDTO sobd);
	
	public boolean isCancel(OrderEntity or);
	public boolean cancelOrderAndListOrderDetail(OrderEntity order);
	public boolean updateOrder(OrderEntity o); 
	
	public List<OrderEntity> getOrderListByFields(SearchOrderByFieldsDTO sobf);
	
	public DashBoardDTO getDashBoard(Integer id);
	public List<OrderEntity> getListOrderDeliveryAndUserDelivery(String status,Integer orgId);
	
	public Boolean saveConfirmDeliveryOrder(OrderEntity order); 
}
