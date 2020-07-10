package com.paLlevar.app.model.services.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paLlevar.app.model.entities.MenuDayEntity;
import com.paLlevar.app.model.entities.MenuDayProductEntity;
import com.paLlevar.app.model.entities.OrderDetailEntity;
import com.paLlevar.app.model.entities.OrderEntity;
import com.paLlevar.app.model.repository.OrderDetailRepository;
import com.paLlevar.app.model.repository.OrderRepository;
import com.paLlevar.app.model.services.MenuDayProductService;
import com.paLlevar.app.model.services.MenuDayService;
import com.paLlevar.app.model.services.OrderDetailService;
import com.paLlevar.app.model.services.OrderService;
import com.paLlevar.app.util.Constants;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private MenuDayProductService menuDayProdService;
	
	@Override
	public List<OrderEntity> getAll() {
		return repo.findAll();
	}

	@Override
	public OrderEntity getOneById(Integer id) {
		return repo.getOne(id);
	}

	@Override
	public OrderEntity save(OrderEntity t) {
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public void processOrder(OrderEntity order) {
		
		
	}

	@Override // actualiza el estado del menu del dia
	public void saveOrderByOrganizationIdAndSucursalId(OrderEntity order) {
		repo.save(order);
		if(order.getOrderDetail() != null) {
			order.getOrderDetail().forEach(od ->{
				od.setStatus(Constants.ORDER_DETAIL_STATUS_PENDING);
				od.setOrganizationId(order.getOrganizationId());
				if(order.getSucursalId() != null) {
					od.setSucursalId(order.getSucursalId());
				}
				od.setUserCreateId(order.getUserCreateId());
				od.setOrder(order);
				MenuDayProductEntity mp =menuDayProdService.getMenuByIdAndStatus(od.getMenuProductId(),Constants.MENUD_PROD_STATUS_AVAILABLE);
				mp.setAvailable(mp.getAvailable()-1);
				if(mp.getAvailable().equals(0)) {
					mp.setStatus(Constants.MENUD_PROD_STATUS_NOT_AVAILABLE);
				}
				menuDayProdService.save(mp);
				od.setCreateDate(LocalDateTime.now());
				orderDetailService.save(od);
				
				if(order.getTotal() !=null && order.getTotal() != 0.0)
					order.setTotal(order.getTotal()+od.getPrice());
				else
					order.setTotal(od.getPrice());
				if(order.getQuantity() !=null)
					order.setQuantity(order.getQuantity() + 1);
				else
					order.setQuantity(1);
				
			});
			
			
		}
		order.setCreateDate(LocalDateTime.now());
		order.setStatus(Constants.ORDER_DETAIL_STATUS_PENDING);
		repo.save(order);
	}

	@Override
	public List<OrderEntity> getListOrderByStatus(String status, OrderEntity order) {
		List<OrderEntity> odList = repo.getListOrderByStatus(status, order.getSucursalId(),order.getOrganizationId());
		for(OrderEntity od :odList) {
			for(OrderDetailEntity odDetail : od.getOrderDetail()) {
				odDetail.setOrderId(od.getId());
			}
		}	
		return odList;	
	}

	@Override
	public Boolean CheckOrder(Integer oid, Integer orgId) {
		List<OrderDetailEntity> odList = orderDetailService.getListOrderDetailByOrderId(oid, orgId);
		Integer checkOrderDetail=0;
		for(OrderDetailEntity odDetail : odList){
			if(odDetail.getStatus().equals(Constants.ORDER_DETAIL_STATUS_PENDING))
				checkOrderDetail++;
			
		}
		if(checkOrderDetail>0 && checkOrderDetail.equals(odList.size()))
			return true;
		else return false;
	}

	@Override
	public List<OrderEntity> getListOrderByNotStatusAndUserId(List<String> status, Integer userId) {
		return repo.getListOrderByNotStatusAndUserId(status, userId);
	}

}
