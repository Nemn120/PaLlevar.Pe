package com.paLlevar.app.model.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paLlevar.app.model.entities.MenuDayEntity;
import com.paLlevar.app.model.entities.OrderEntity;
import com.paLlevar.app.model.repository.OrderRepository;
import com.paLlevar.app.model.services.MenuDayService;
import com.paLlevar.app.model.services.OrderService;
import com.paLlevar.app.util.Constants;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private MenuDayService menuDayService;
	
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

	@Override
	public void saveOrderByOrganizationIdAndSucursalId(OrderEntity order) {
		
		MenuDayEntity mday = new MenuDayEntity();// =menuDayService.getMenuDayByDayAndOrganizationIdAndSucursalId(order.sucursalId,order.organizationId);
		mday.getMenuDayProduct().forEach(menuProduct->{
			order.getOrderDetail().forEach(orderDetail ->{

				
				if(menuProduct.getProduct().getId() == orderDetail.getProduct().getId()) {
					// HIJO
					menuProduct.setAvailable(menuProduct.getAvailable()-1);
					orderDetail.setStatus(Constants.ORDER_DETAIL_STATUS_PENDING);
					if(menuProduct.getAvailable().equals(0)) {
						menuProduct.setStatus(Constants.MENUDAY_STATUS_NOT_AVAILABLE);
					}
						
				}
				
			});
		});
		Date fechaActual = new Date();
		// PADRE,
		order.setStatus(Constants.ORDER_STATUS__PENDING);
		menuDayService.save(mday);
		repo.save(order);
	}

	@Override
	public List<OrderEntity> getListOrderByStatus(String status, Integer org, Integer sucursal) {
		List<OrderEntity> odList = null;//= repo.getListOrderByStatus(status, org, sucursal);
		
		return odList;
		
	}

}
