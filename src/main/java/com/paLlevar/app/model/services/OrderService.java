package com.paLlevar.app.model.services;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.OrderDetailEntity;
import com.paLlevar.app.model.entities.OrderEntity;
import com.paLlevar.app.model.services.dto.SearchOrderByDeliveryManDTO;

@Service
public interface OrderService extends GenericCRUD<OrderEntity, Integer>{

	public void processOrder(OrderEntity order);
	public OrderEntity saveOrderByOrganizationIdAndSucursalId(OrderEntity order);
	public List<OrderEntity> getListOrderByStatus(String status,OrderEntity order);
	public Boolean CheckOrder(Integer oid,  Integer orgId);
	public List<OrderEntity> getListOrderByNotStatusAndUserId(List<String> status, Integer userId);
	public List<OrderEntity> getListOrderStatusAndOrgId(List<String> status, Integer orgId);
	public void deliveryOrder(OrderEntity order);
	public void attendOrder(OrderEntity order);
	
	public List<OrderEntity> getOrderListByDeliveyId(SearchOrderByDeliveryManDTO sobd);
	
	public Boolean isCancel(OrderEntity or);
	public Boolean deleteOrderAndListOrderDetail(OrderEntity order);
}
