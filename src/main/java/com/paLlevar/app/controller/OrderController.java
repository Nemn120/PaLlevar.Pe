package com.paLlevar.app.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paLlevar.app.model.entities.OrderEntity;
import com.paLlevar.app.model.services.OrderService;
import com.paLlevar.app.model.services.PlaceService;
import com.paLlevar.app.model.services.dto.SearchOrderByDeliveryManDTO;

import com.paLlevar.app.model.services.dto.DashBoardDTO;
import com.paLlevar.app.model.services.dto.SearchOrderByDeliveryManDTO;
import com.paLlevar.app.model.services.dto.SearchOrderByFieldsDTO;
import com.paLlevar.app.util.Constants;

@RestController
@RequestMapping("/order")
public class OrderController {

	String path = "http://localhost:8080/order";
	
	private static final Logger logger = LogManager.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PlaceService placeService;
	
	
	@PostMapping(path="/cor")
	public ResponseEntity<?> cancelOrder(@RequestBody OrderEntity or){
		logger.info("OrderController.cancelOrder()");
		Map<String,Object> response = new HashMap<>();
		boolean check = orderService.isCancel(or);
		if(check){
			Boolean checkDelete = orderService.cancelOrderAndListOrderDetail(or);
			if(checkDelete) {
				logger.warn("Pedido:"+or.getId() + " cancelado");
				response.put(Constants.MESSAGE_BODY_RESPONSE, "Se canceló correctamente su orden");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
			}
			else { 
				logger.warn("Pedido:"+or.getId() + " no pudo ser cancelado");
				response.put(Constants.MESSAGE_BODY_RESPONSE, "Error al cancelar la orden");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else {
			logger.warn("Pedido:"+or.getId() + " excedio el limite de tiempo");
			response.put(Constants.MESSAGE_BODY_RESPONSE, "Error al cancelar la orden, el pedido excedió el limite de tiempo permitido");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path="/glo")
	public ResponseEntity<List<OrderEntity>>  getListOrder(){
		logger.info("OrderController.getListOrder()");
		List<OrderEntity> lista = orderService.getAll();
		logger.info("Info level log message");
		logger.debug("Debug level log message");
		logger.error("Error level log message");
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
		
	}
	
	@PostMapping(path="/so")
	public OrderEntity saveOrder(@RequestBody OrderEntity or) {
		logger.info("OrderController.saveOrder()");
		OrderEntity orderSave = orderService.save(or);
		return orderSave;
	}
	
	@DeleteMapping(value="/do/{id}")
	public void deletedOrder(@PathVariable("id")Integer id) {
		logger.info("OrderController.deletedOrder()");
		orderService.deleteById(id);
	}
	
	@GetMapping(value="/globu/{id}")
	public ResponseEntity<List<OrderEntity>>  getListOrderByUserIdAndNotStatus(@PathVariable("id")Integer id){
		logger.info("OrderController.getListOrderByUserIdAndNotStatus()");
		List<String> statusList = Arrays.asList(Constants.ORDER_DETAIL_STATUS_CANCEL,Constants.ORDER_STATUS_DELIVERED);
		List<OrderEntity> lista = orderService.getListOrderByNotStatusAndUserId(statusList, id);
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
	}
	
	@PostMapping(path="/sobos")
	public ResponseEntity<?> saveNewOrderByOrganizationId(@RequestBody OrderEntity or) {
		logger.info("OrderController.saveNewOrderByOrganizationId()");
		Map<String,Object> response = new HashMap<>();
		try {
			if(or.getPlace() != null)
				placeService.save(or.getPlace());
			response.put(Constants.DATA_RESPONSE, orderService.saveOrderByOrganizationIdAndSucursalId(or));
			response.put("message", "Pedido registrado con éxito");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}catch(Exception e){
			response.put("error", "Error al realizar pedido");
			logger.error("ERORR ==> ",e);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path="/sdoo")
	public  ResponseEntity<OrderEntity> saveDeliveryOrderByOrg(@RequestBody OrderEntity or) {
		logger.info("OrderController.saveDeliveryOrderByOrg()");
		orderService.deliveryOrder(or);
		return new ResponseEntity<OrderEntity> (new OrderEntity(),HttpStatus.OK);
	}
	
	@PostMapping(path="/saoo")
	public  ResponseEntity<OrderEntity> saveAttendOrderByOrg(@RequestBody OrderEntity or) {
		logger.info("OrderController.saveAttendOrderByOrg()");
		orderService.attendOrder(or);
		return new ResponseEntity<OrderEntity> (new OrderEntity(),HttpStatus.OK);
	}
	
	
	@PostMapping(path="/cho")
	public ResponseEntity<Object>CheckOrder(@RequestBody OrderEntity or){
		logger.info("OrderController.CheckOrder()");
		Boolean verificar= orderService.CheckOrder(or.getId(), or.getOrganizationId());
		if(verificar)
			return new ResponseEntity<Object>(HttpStatus.OK);
		else 
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(path="/gloa")
	public ResponseEntity<List<OrderEntity>> getListOrderAttent(@RequestBody OrderEntity or){
		logger.info("OrderController.getListOrderAttent()");
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_STATUS_ATTENT, or);
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
	}
	
	@PostMapping(path="/glop")
	public ResponseEntity<List<OrderEntity>>  getListOrderPendding(@RequestBody OrderEntity or){
		logger.info("OrderController.getListOrderPendding()");
		List<String> statusList = Arrays.asList(Constants.ORDER_STATUS_PROCESS,Constants.ORDER_STATUS__PENDING);
		List<OrderEntity> lista = orderService.getListOrderStatusAndOrgId(statusList,or.getOrganizationId());
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
		
	}
	
	@PostMapping(path="/glody")
	public ResponseEntity<List<OrderEntity>>  getListOrderDelivery(@RequestBody OrderEntity or){
		logger.info("OrderController.getListOrderDelivery()");
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_STATUS_DELIVERY,or);
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
		
	}
	
	@PostMapping(path="/gloc")
	public ResponseEntity<List<OrderEntity>> getListOrderCancel(@RequestBody OrderEntity or){
		logger.info("OrderController.getListOrderCancel()");
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_STATUS_CANCEL,or);
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
	}

	@PostMapping(path="/glopr")
	public ResponseEntity<List<OrderEntity>> getListOrderProcess(@RequestBody OrderEntity or){
		logger.info("OrderController.getListOrderProcess()");
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_STATUS_PROCESS,or);
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
	}

	@PostMapping(path="/gloe")
	public ResponseEntity<List<OrderEntity>> getListOrderError(@RequestBody OrderEntity or){
		logger.info("OrderController.getListOrderError()");
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_STATUS_ERROR, or);
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
	}
	
	@PostMapping(path="/glod")
	public ResponseEntity<List<OrderEntity>>  getListOrderDelivered(@RequestBody OrderEntity or){
		logger.info("OrderController.getListOrderDelivered()");
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_STATUS_DELIVERED,or);
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
		
	}
	
	@PostMapping(path="/golbdi")
	public ResponseEntity<?>  getOrderListByDeliveryId(@RequestBody SearchOrderByDeliveryManDTO sobd){
		logger.info("OrderController.getOrderListByDeliveryId()");
		Map<String,Object> response = new HashMap<>();
		try {
			List<OrderEntity> orderList =  orderService.getOrderListByDeliveryId(sobd);
			
			response.put("dataList", orderList);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		}catch(Exception e){
			response.put(Constants.MESSAGE_BODY_RESPONSE, "Error");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping(path="/upor")
	public ResponseEntity<?> updateOrder(@RequestBody OrderEntity o){
		logger.info("OrderController.updateOrder()");
		Map<String,Object> response = new HashMap<>();
		try {
			if(orderService.updateOrder(o)) {
			response.put(Constants.MESSAGE_BODY_RESPONSE, "Los datos del pedido fueron actualizados con éxito");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);}
			else {
				response.put(Constants.MESSAGE_BODY_RESPONSE, "Error al actualizar el pedido");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
			}
		}catch(Exception e) {
			response.put(Constants.MESSAGE_BODY_RESPONSE, "Error al actualizar pedido");
			logger.error("ERROR ==>", e);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path="/golbf")
	public ResponseEntity<?>  getOrderListByFields(@RequestBody SearchOrderByFieldsDTO sobf){
		logger.info("OrderController.getOrderListByFields()");
		Map<String,Object> response = new HashMap<>();
		try {
			List<OrderEntity> orderList =  orderService.getOrderListByFields(sobf);
			
			response.put("dataList", orderList);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		}catch(Exception e){
			response.put(Constants.MESSAGE_BODY_RESPONSE, "Error");
			logger.error("ERROR ==>", e);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@GetMapping(value="/gdb/{id}")
	public ResponseEntity<?>  getDashBoard(@PathVariable("id")Integer id){
		logger.info("OrderController.getDashBoard()");
		Map<String,Object> response = new HashMap<>();
		try {
			DashBoardDTO dash =  orderService.getDashBoard(id);
			
			response.put("data", dash);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		}catch(Exception e){
			response.put(Constants.MESSAGE_BODY_RESPONSE, "Error");
			logger.error("ERROR ==>", e);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
