package com.paLlevar.app.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.OrderDetailEntity;
import com.paLlevar.app.model.entities.OrderEntity;
import com.paLlevar.app.model.entities.UserEntity;
import com.paLlevar.app.model.repository.OrderDetailRepository;
import com.paLlevar.app.model.services.OrderDetailService;
import com.paLlevar.app.model.services.OrderService;
import com.paLlevar.app.util.Constants;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	
	@Autowired
	private OrderDetailRepository repo;
	
	@Autowired
	private OrderService orderService;

	@Override
	public List<OrderDetailEntity> getAll() {
		return repo.findAll();
	}

	@Override
	public OrderDetailEntity getOneById(Integer id) {
		return repo.getOne(id);
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
		repo.save(orderDetail);
		updatedListOrderDetail(orderDetail);
	}
	
	private  void updatedListOrderDetail(OrderDetailEntity orderDetail) {
		Integer countOdByStatus=0;
		OrderEntity od = orderService.getOneById(orderDetail.getId());
			
		for(OrderDetailEntity odDetail : od.getOrderDetail()){
				if(orderDetail.getStatus().equals(odDetail.getStatus())){
					countOdByStatus++;
				}
		}
	
	if(countOdByStatus.equals(od.getOrderDetail().size())) {
		od.setStatus(orderDetail.getStatus());
	}else {
		od.setStatus(Constants.ORDER_STATUS_PROCESS);
	}
	}

	@Override
	public OrderDetailEntity getOrderDetailByStatusAndId(String status, Integer id, Integer organization, Integer sucursal) {
		OrderDetailEntity orderdetail = repo.getOrderDetailByStatusAndId(status, id, organization, sucursal);
		return orderdetail;
	}

	@Override
	public List<OrderDetailEntity> getListOrderDetailByStatus(String status, Integer org, Integer suc) {
		List<OrderDetailEntity> odList = repo.getListOrderDetailByStatus(status, org, suc);
		return odList;
	}
	
	
	@Override
	public void assignDeliveryMan(Integer idOrder, Integer idDeliveryMan, Integer idSuc, Integer idOrg) {
		
		OrderDetailEntity or = repo.getOrderDetailById(idOrder,idSuc,idOrg);
		UserEntity u = repo.getUserbyOrganitationDyIDBySucursal(idDeliveryMan, idSuc, idOrg);
		or.setUserDelivery(u);
		repo.save(or);
		
	}
}


