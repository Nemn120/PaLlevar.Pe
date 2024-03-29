package com.paLlevar.app.model.services.impl;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paLlevar.app.model.entities.MenuDayProductEntity;
import com.paLlevar.app.model.entities.OrderDetailEntity;
import com.paLlevar.app.model.entities.OrderEntity;
import com.paLlevar.app.model.entities.PlaceEntity;
import com.paLlevar.app.model.entities.UserEntity;
import com.paLlevar.app.model.repository.OrderRepository;
import com.paLlevar.app.model.services.CompanyService;
import com.paLlevar.app.model.services.MenuDayProductService;
import com.paLlevar.app.model.services.OrderDetailService;
import com.paLlevar.app.model.services.OrderService;
import com.paLlevar.app.model.services.UserService;
import com.paLlevar.app.model.services.dto.DashBoardDTO;
import com.paLlevar.app.model.services.dto.SearchOrderByDeliveryManDTO;
import com.paLlevar.app.model.services.dto.SearchOrderByFieldsDTO;
import com.paLlevar.app.util.Constants;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);	

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
	
	@Override
	public List<OrderEntity> saveOrderByManyOrganization(OrderEntity order) {
		Map<Integer,List<OrderDetailEntity>> mapOrderDetail = new HashMap<Integer,List<OrderDetailEntity>>();
		List<OrderEntity> orderResult = new ArrayList<OrderEntity>();
		order.getOrderDetail().forEach(orderDetail ->{
			if(orderDetail != null) {
				if(mapOrderDetail.containsKey(orderDetail.getOrganizationId())) {
					mapOrderDetail.get(orderDetail.getOrganizationId()).add(orderDetail);
				}else {
					List<OrderDetailEntity> odList = new ArrayList<OrderDetailEntity>();
					odList.add(orderDetail);
					mapOrderDetail.put(orderDetail.getOrganizationId(),odList);
				}
			}
		});
		
		for (Map.Entry<Integer, List<OrderDetailEntity>> entry : mapOrderDetail.entrySet()) {
		    OrderEntity orderCompany = new OrderEntity();
		    BeanUtils.copyProperties(order, orderCompany);
		    orderCompany.setOrderDetail(new ArrayList<OrderDetailEntity>());
		    orderCompany.setOrderDetail(entry.getValue());
		    orderCompany.setCompanyName(companyService.getOneById(entry.getKey()).getNombre());
		    orderCompany.setOrganizationId(entry.getKey());
		    orderResult.add(this.saveOrderByOrganizationIdAndSucursalId(repo.save(orderCompany)));
		}
		
		return orderResult;
	}

	@Override
	public OrderEntity saveOrderByOrganizationIdAndSucursalId(OrderEntity order) {
		logger.info("OrderServiceImpl.saveOrderByOrganizationIdAndSucursalId()");
			order.getOrderDetail().forEach(od ->{
				od.setStatus(Constants.ORDER_DETAIL_STATUS_PENDING);
				od.setOrganizationId(order.getOrganizationId());
				od.setUserCreateId(order.getUserOrder().getId());
				od.setOrder(order);
				MenuDayProductEntity mp =menuDayProdService.getMenuByIdAndStatus(od.getMenuProductId(),Constants.MENUD_PROD_STATUS_AVAILABLE);
				mp.setAvailable(mp.getAvailable()-1);
				if(mp.getAvailable().equals(0)) {
					mp.setStatus(Constants.MENUD_PROD_STATUS_NOT_AVAILABLE);
					logger.trace("Menu: "+mp.getId() + " estado : "+Constants.MENUD_PROD_STATUS_NOT_AVAILABLE);
				}
				menuDayProdService.save(mp);
				od.setCreateDate(LocalDateTime.now());
				orderDetailService.save(od);
				logger.trace("Platillo : "+od.getProduct().getName() + " se registro con exito");
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

		logger.trace("Orden : "+order.getId()+ " estado : "+Constants.ORDER_DETAIL_STATUS_PENDING);
		return repo.save(order);
	}

	@Override 
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
	public List<OrderEntity> getListOrderAttend(String status, OrderEntity order){
		return repo.getListOrderStatusAttendAndOrgId(status, order.getOrganizationId());
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
	public boolean deliveryOrder(OrderEntity order) {
		logger.info("OrderServiceImpl.deliveryOrder()");
		UserEntity userDelivery = userService.getOneById(order.getUserDeliveryId());
		if(userDelivery == null) {
			logger.error("El delivery:"+order.getId()+ " no existe en la BD");
			return false;	
		}
		if(order.getOrderDetail() == null){
			logger.error("La orden:"+order.getId()+ " no contiene platos a entregar");
			return false;
		}
		order.getOrderDetail().forEach(od->{
			od.setStatus(Constants.ORDER_DETAIL_STATUS_DELIVERY);
			od.setDeliveryDate(new Date());
			od.setUserDelivery(new UserEntity());
			od.setOrder(order);
			od.getUserDelivery().setId(order.getUserDeliveryId());
			orderDetailService.save(od);
		});
		userDelivery.setStatus(Constants.DELIVERY_MANY_STATUS_OCUPADO);
		userService.save(userDelivery);
		logger.trace("Delivery Man : "+userDelivery.getId()+ " cambio a  estado : "+Constants.DELIVERY_MANY_STATUS_OCUPADO);
		order.setDeliveryDate(new Date());
		order.setStatus(Constants.ORDER_STATUS_DELIVERY);
		logger.trace("Orden: "+order.getId()+ " cambio a  estado : "+Constants.ORDER_STATUS_DELIVERY);
		repo.save(order);
		return true;
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
		logger.info("OrderServiceImpl.cancelOrderAndListOrderDetail()");
		try {			
			orderDetailService.updateOrderDetailStatus(order.getId(), Constants.ORDER_DETAIL_STATUS_CANCEL);
			repo.updateOrderStatus(order.getId(), Constants.ORDER_STATUS_CANCEL);
			logger.trace("Orden : "+order.getId()+ " estado: "+Constants.ORDER_STATUS_CANCEL);
			return true;
		}catch(Exception e) {
			logger.trace("Orden : "+order.getId()+" no pudo ser cancelado");
			return false;
		}
	}

	@Override
	public boolean updateOrder(OrderEntity o) {
		if(o.getStatus().equals(Constants.ORDER_STATUS__PENDING) || o.getStatus().equals(Constants.ORDER_STATUS_PROCESS)) {
			repo.updateOrder(o.getId(), o.getPhone(), o.getAddress(), o.getReference());
			return true;
		}else
			return false;
	}

	@Override
	public List<OrderEntity> getOrderListByFields(SearchOrderByFieldsDTO sobf) {
		return  repo.getOrderListByFields(sobf);
	}

	@Override
	public DashBoardDTO getDashBoard(Integer id) {
		logger.info("OrderServiceImpl.getDashBoard()");
		DashBoardDTO dash = new DashBoardDTO();
		LocalDateTime time = LocalDateTime.now();
		LocalDateTime initDatet =time.withHour(0).withMinute(0).withSecond(0);
		LocalDateTime finalDatet =time.withHour(23).withMinute(59).withSecond(59);
		Double salesToday = repo.getSales(id, initDatet, finalDatet);
		Integer quantityToday = repo.getQuantity(id, initDatet, finalDatet);
		dash.setQuantityToday(quantityToday);
		dash.setSalesToday(salesToday);
		logger.trace("Today started: "+initDatet+" Today finished: "+finalDatet);
		LocalDateTime initDatey =initDatet.minusDays(1);
		LocalDateTime finalDatey =finalDatet.minusDays(1);
		Double salesYesterday = repo.getSales(id, initDatey, finalDatey);
		Integer quantityYesterday = repo.getQuantity(id, initDatey, finalDatey);
		dash.setQuantityYesterday(quantityYesterday);
		dash.setSalesYesterday(salesYesterday);
		logger.trace("Yesterday started: "+initDatey+" Yesterday finished: "+finalDatey);
		
		if(salesYesterday != null && salesToday!=null) {
		Double variationSalesDays = (((double)salesToday-(double)salesYesterday)/(double)salesYesterday)*100;
		dash.setSalesVariationDay(variationSalesDays);
		}
		if(quantityYesterday != null && quantityToday!=null) {
			Double variationQuantityDays = ((((double)quantityToday-(double)quantityYesterday)/(double)quantityYesterday)*100);
			dash.setQuantityVariationDay(variationQuantityDays);
		}
		
		LocalDateTime initDatelw =time.withHour(0).withMinute(0).withSecond(0).minusDays(7+time.getDayOfWeek().getValue()-1);
		LocalDateTime finalDatelw =time.withHour(23).withMinute(59).withSecond(59).minusDays(time.getDayOfWeek().getValue());
		Double salesLastWeek = repo.getSales(id, initDatelw, finalDatelw);
		Integer quantityLastWeek = repo.getQuantity(id, initDatelw, finalDatelw);
		dash.setQuantityLastWeek(quantityLastWeek);
		dash.setSalesLastWeek(salesLastWeek);
		logger.trace("Last week started: "+initDatelw+" Last week finished: "+finalDatelw);
		LocalDateTime initDatetw =initDatelw.plusWeeks(1);
		LocalDateTime finalDatetw =finalDatelw.plusWeeks(1);
		Double salesThisWeek = repo.getSales(id, initDatetw, finalDatetw);
		Integer quantityThisWeek = repo.getQuantity(id, initDatetw, finalDatetw);
		dash.setQuantityThisWeek(quantityThisWeek);
		dash.setSalesThisWeek(salesThisWeek);
		logger.trace("week started: "+initDatetw+" week finished: "+finalDatetw);

		
		if(salesLastWeek !=null && salesThisWeek !=null) {
			Double variationSalesWeek = (((double)salesThisWeek-(double)salesLastWeek)/(double)salesLastWeek)*100;
			variationSalesWeek = (double) Math.round(variationSalesWeek * 100);
			dash.setSalesVariationWeek(variationSalesWeek/100);
		}
		if(quantityLastWeek != null && quantityThisWeek != null) {
			Double variationQuantityWeeks =  ((((double)quantityThisWeek-(double)quantityLastWeek)/(double)quantityLastWeek)*100);
			logger.trace("Last: "+quantityLastWeek+"\nThis: "+quantityThisWeek+"\nTotal: "+variationQuantityWeeks);
			variationQuantityWeeks = (double) Math.round(variationQuantityWeeks * 100);
			dash.setQuantityVariationWeek(variationQuantityWeeks/100);
		}
		
		
		dash.setOrderPending(repo.getListOrderRecentByStatusLimitedTo(5,Constants.ORDER_STATUS__PENDING, id));
		dash.setOrderDelivery(repo.getListOrderRecentByStatusLimitedTo(5,Constants.ORDER_STATUS_DELIVERY, id));
		dash.setOrderDelivered(repo.getListOrderRecentByStatusLimitedTo(5,Constants.ORDER_STATUS_DELIVERED, id));
		
		return dash;
	}

	@Override
	public List<OrderEntity> getListOrderDeliveryAndUserDelivery(String status, Integer orgId) {
		List<OrderEntity> orderList = new ArrayList<OrderEntity>();  
		orderList=repo.findByOrganizationIdAndStatus(orgId,status);
		Map<Integer,UserEntity> userDeliveryList = new HashMap<Integer,UserEntity>() ;
		if(orderList.isEmpty())
			return null;
		orderList.forEach(order ->{
			if(order.getUserDeliveryId() != null) {
				order.setUserDelivery(new UserEntity());
				if(userDeliveryList.containsKey(order.getUserDeliveryId())) {
					order.setUserDelivery(userDeliveryList.get(order.getUserDeliveryId()));
				}else {
					UserEntity userDelivery =userService.getOneById(order.getUserDeliveryId());
					userDeliveryList.put(userDelivery.getId(),userDelivery);
					order.setUserDelivery(userDelivery);
				}
			}
		});
		return orderList;
	}

	@Override
	public Boolean saveConfirmDeliveryOrder(OrderEntity order) {
		try {
		order.getOrderDetail().forEach(orderDetail ->{
			orderDetailService.updateOrderDetailStatus(orderDetail.getId(),Constants.ORDER_DETAIL_STATUS_DELIVERED);
		});
		repo.updateConfirmOrderById(order.getId(),Constants.ORDER_STATUS_DELIVERED);
		userService.updateStatusById(order.getUserDeliveryId(), Constants.DELIVERY_MAN_STATUS_DISPONIBLE);
		return true;
		}catch(Exception e) {
			return false;
		}
	}

	
}

