package com.paLlevar.app.model.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paLlevar.app.model.entities.OrderDetailEntity;
import com.paLlevar.app.model.entities.OrderEntity;
import com.paLlevar.app.model.entities.UserEntity;
import com.paLlevar.app.model.repository.OrderDetailRepository;
import com.paLlevar.app.model.services.OrderDetailService;
import com.paLlevar.app.model.services.OrderService;
import com.paLlevar.app.model.services.UserService;
import com.paLlevar.app.model.services.dto.SearchSalesByFieldsDTO;
import com.paLlevar.app.util.Constants;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {
	
	@Autowired
	private OrderDetailRepository repo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;

	@Override
	public List<OrderDetailEntity> getAll() {
		return repo.findAll();
	}

	@Override
	public OrderDetailEntity getOneById(Integer id) {
		return repo.findById(id).orElse(new OrderDetailEntity());
	}

	@Override
	public OrderDetailEntity save(OrderDetailEntity t) {
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public void saveAttendOrderDetail(OrderDetailEntity orderDetail) {
		orderDetail.setOrder(new OrderEntity());
		orderDetail.getOrder().setId(orderDetail.getOrderId());
		//repo.save(orderDetail);
		updatedListOrderDetail(orderDetail);
	}
	
	private  void updatedListOrderDetail(OrderDetailEntity orderDetail) {
		Integer countOdByStatus=1;
		OrderEntity od = orderService.getOneById(orderDetail.getOrderId());
			
		for (OrderDetailEntity odDetail : od.getOrderDetail()) {
			if (orderDetail.getStatus().equals(odDetail.getStatus())) {//
				countOdByStatus++;
			}
			if (orderDetail.getAttendDate() != null) { // si ya fue atendido
				orderDetail.setDeliveryDate(new Date());
				
			}
			if (orderDetail.getAttendDate() == null) { // si todavia no ha sido atendido
				orderDetail.setAttendDate(new Date());
			}
		}
		repo.save(orderDetail);
	// si todos los pedidos han sido atendido o entregados
	if(countOdByStatus.equals(od.getOrderDetail().size())) {
		od.setStatus(orderDetail.getStatus()); // actualzia al padre el estado,
		if(od.getAttendDate() != null) {	// actualiza el padre a entregado
			od.setDeliveryDate(new Date());
			UserEntity userDelivery = userService.getOneById(orderDetail.getUserDelivery().getId());
			userDelivery.setStatus(Constants.DELIVERY_MANY_STATUS_OCUPADO);
			userService.save(userDelivery);
		}
		if(od.getAttendDate() == null) { // actualizael padre a atendido
			od.setAttendDate(new Date());
		}
	}else {
		od.setStatus(Constants.ORDER_STATUS_PROCESS);
	}
	orderService.save(od);
	}
	@Override
	public OrderDetailEntity getOrderDetailByStatusAndId(String status, Integer id, Integer organization) {
		OrderDetailEntity orderdetail = repo.getOrderDetailByStatusAndId(status, id, organization);
		return orderdetail;
	}


	@Override
	public List<OrderDetailEntity> getListOrderDetailByStatus(String status, Integer org) {
		List<OrderDetailEntity> odList = repo.getListOrderDetailByStatus(status, org);
		return odList;
	}
	
	@Override
	public List<OrderDetailEntity> getListOrderDetailByOrderId(Integer oid, Integer orgId) {
		return repo.getListOrderDetailByOrderId(oid, orgId); 
	}

	@Override
	public List<Map<String, Object>> getSalesByFieldsGroupByMenuProduct(SearchSalesByFieldsDTO ssbf) {
		List<Object[]> listResult = repo.getSalesByFieldsGroupByMenuProduct(ssbf);
		//
		if(listResult != null) {
			List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
			AtomicInteger index = new AtomicInteger();
			listResult.forEach(res ->{
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("index",index.incrementAndGet());
				map.put("productName",res[0]);
				map.put("categoryName",res[1]);
				map.put("price",res[2]);
				map.put("countSales",res[3]);
				map.put("sumSales",res[4]);
				map.put("menuProductId",res[5]);
				
				result.add(map);
			});
			return result;
		}
		return null;
	}

	@Override
	public boolean updateOrderDetailStatus(Integer id, String status) {
		try{
			repo.updateOrderDetailStatus(id, status);
			return true;
		}catch(Exception e) {

			return false;
		}
	}

}


