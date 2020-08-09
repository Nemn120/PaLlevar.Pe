package com.paLlevar.app.model.services.impl;

import java.time.LocalDateTime;

import java.util.Date;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.paLlevar.app.model.entities.MenuDayProductEntity;
import com.paLlevar.app.model.entities.OrderDetailEntity;
import com.paLlevar.app.model.entities.OrderEntity;
import com.paLlevar.app.model.entities.UserEntity;
import com.paLlevar.app.model.repository.OrderRepository;
import com.paLlevar.app.model.services.CompanyService;
import com.paLlevar.app.model.services.MenuDayProductService;
import com.paLlevar.app.model.services.OrderDetailService;
import com.paLlevar.app.model.services.OrderService;
import com.paLlevar.app.model.services.UserService;
import com.paLlevar.app.model.services.dto.SearchOrderByDeliveryManDTO;
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
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CompanyService companyService;
	
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
	public OrderEntity saveOrderByOrganizationIdAndSucursalId(OrderEntity order) {
		order.setOrganizationId(order.getOrderDetail().get(0).getOrganizationId());
		order.setCompanyName(companyService.getOneById(order.getOrderDetail().get(0).getOrganizationId()).getNombre());
		repo.save(order);
			order.getOrderDetail().forEach(od ->{
				od.setStatus(Constants.ORDER_DETAIL_STATUS_PENDING);
				od.setOrganizationId(order.getOrganizationId());
				od.setUserCreateId(order.getUserOrder().getId());
				od.setOrder(order);
				//od.setCompanyName(companyService.getOneById();
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
			
			
		order.setCreateDate(LocalDateTime.now());
		order.setStatus(Constants.ORDER_DETAIL_STATUS_PENDING);
		return repo.save(order);
	}

	@Override // TRA DE UNA SOLA ORGANIZACION 
	public List<OrderEntity> getListOrderByStatus(String status, OrderEntity order) {
		List<OrderEntity> odList = repo.findByOrganizationIdAndStatus(order.getOrganizationId(),status);
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

	@Override
	public void deliveryOrder(OrderEntity order) {
		order.getOrderDetail().forEach(od->{
			od.setStatus(Constants.ORDER_DETAIL_STATUS_DELIVERY);
			od.setDeliveryDate(new Date());
			od.setUserDelivery(new UserEntity());
			od.setOrder(order);
			od.getUserDelivery().setId(order.getUserDeliveryId());
			UserEntity userDelivery = userService.getOneById(order.getUserDeliveryId());
			userDelivery.setStatus(Constants.DELIVERY_MANY_STATUS_OCUPADO);
			userService.save(userDelivery);
			
			orderDetailService.save(od);
		});
		order.setDeliveryDate(new Date());
		order.setStatus(Constants.ORDER_STATUS_DELIVERY);
		repo.save(order);
		
	}

	@Override
	public void attendOrder(OrderEntity order) {
		order.getOrderDetail().forEach(od ->{
			if(order.getUserAttendId()!= null) {
				od.setOrder(order);
				od.setAttendDate(new Date());
				od.setStatus(Constants.ORDER_DETAIL_STATUS_ATTENT);
				od.setUserAttend(new UserEntity());
				od.getUserAttend().setId(order.getUserAttendId());
				
				orderDetailService.save(od);
			}
		});
		OrderEntity oe = repo.findById(order.getId()).get();
		long cantidad = oe.getOrderDetail().stream().filter(x ->
			Constants.ORDER_DETAIL_STATUS_ATTENT.equals(x.status)).count();
		if(cantidad == oe.getOrderDetail().size()) {
			oe.setStatus(Constants.ORDER_STATUS_ATTENT);
			oe.setAttendDate(new Date());
		}
		else
			oe.setStatus(Constants.ORDER_STATUS_PROCESS);
		repo.save(oe);
	}

	@Override
	public List<OrderEntity> getListOrderStatusAndOrgId(List<String> status, Integer orgId) {
		return repo.getListOrderByStatusAndOrgId(status, orgId);
	}

	@Override
	public List<OrderEntity> getOrderListByDeliveryId(SearchOrderByDeliveryManDTO sobd) {
		return  repo.getOrderListByDeliveryId(sobd);
		
	}
	
	@Override
	public boolean isCancel(OrderEntity or) {
		OrderEntity order = this.getOneById(or.getId());
		if(order.getStatus().equals(Constants.ORDER_STATUS__PENDING)) {
			
			LocalDateTime time = LocalDateTime.now();
			LocalDateTime timelimit = order.getCreateDate().plusMinutes(5);

			return time.isBefore(timelimit);
		}
		return false;
	}
	
	@Override
	public boolean cancelOrderAndListOrderDetail(OrderEntity order) {
		
		try {			
			orderDetailService.updateOrderDetailStatus(order.getId(), Constants.ORDER_DETAIL_STATUS_CANCEL);
			repo.updateOrderStatus(order.getId(), Constants.ORDER_STATUS_CANCEL);
			return true;
			
		}catch(Exception e) {
			
			return false;
		}
	}

	@Override
	public void updateOrder(OrderEntity o) {
		repo.updateOrder(o.getId(), o.getPhone(), o.getAddress(), o.getReference());
	}
	

}
