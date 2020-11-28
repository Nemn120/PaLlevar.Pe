package com.paLlevar.app.controller;

import java.time.LocalDateTime;
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

import com.paLlevar.app.controller.response.GenericResponse;
import com.paLlevar.app.model.entities.OrderDetailEntity;
import com.paLlevar.app.model.entities.OrderEntity;
import com.paLlevar.app.model.entities.UserEntity;
import com.paLlevar.app.model.services.OrderDetailService;
import com.paLlevar.app.model.services.dto.SearchSalesByFieldsDTO;
import com.paLlevar.app.util.Constants;

@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {

	String path = "http://localhost:8080/orderdetail";
	private static final Logger logger = LogManager.getLogger(OrderDetailController.class);	


	@Autowired
	private OrderDetailService orderdetailService;

	@GetMapping(path="/glod")
	public ResponseEntity<List<OrderDetailEntity>>  getListOrderDetail(){
		logger.info("OrderDetailController.getListOrderDetail()");

		List<OrderDetailEntity> lista = orderdetailService.getAll();
		return new ResponseEntity<List<OrderDetailEntity>>(lista,HttpStatus.OK);
		
	}
	
	@PostMapping(path="/sod")
	public OrderDetailEntity saveOrderDetail(@RequestBody OrderDetailEntity order) {
		logger.info("OrderDetailController.saveOrderDetail()");

		OrderDetailEntity orderdetailSave = orderdetailService.save(order);
		return orderdetailSave;
	}
	
	@DeleteMapping(value="/dod/{id}")
	public void deletedOrderDetail(@PathVariable("id")Integer id) {
		logger.info("OrderDetailController.deletedOrderDetail()");

		orderdetailService.deleteById(id);
	}
	
	
	@PostMapping(path="/saod")
	public OrderDetailEntity saveAttendOrderDetail(@RequestBody OrderDetailEntity order) {
		logger.info("OrderDetailController.saveAttendOrderDetail()");

		order.setStatus(Constants.ORDER_DETAIL_STATUS_ATTENT);
		orderdetailService.saveAttendOrderDetail(order);
		return new OrderDetailEntity();
	}
	
	@PostMapping(path="/sdod")
	public OrderDetailEntity saveDeliveryOrderDetail(@RequestBody OrderDetailEntity order) {
		logger.info("OrderDetailController.saveDeliveryOrderDetail()");

		order.setStatus(Constants.ORDER_DETAIL_STATUS_DELIVERY);
		orderdetailService.saveAttendOrderDetail(order);
		return new OrderDetailEntity();
	}
	
	
	@PostMapping(path="/glodbo") // PENDIENTE DE CORRECCION
	public ResponseEntity<?>  getListOrderDetailByOrderId(@RequestBody OrderEntity order){
		logger.info("OrderDetailController.getListOrderDetailByOrderId()");
		Map<String,Object> response = new HashMap<String,Object>();
		
		try {
			response.put("data", orderdetailService.getListOrderDetailByOrderId(order.getId(), order.getOrganizationId()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

		}catch(Exception e) {
			logger.error("EROR ==> ",e);
			response.put(Constants.MESSAGE_BODY_RESPONSE, "Error al realizar la peticion");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path="/admo")
	public ResponseEntity<Object> assignDeliveryManToOrder(@RequestBody OrderDetailEntity order) {
		logger.info("OrderDetailController.assignDeliveryManToOrder()");

		orderdetailService.save(order);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	@PostMapping(path="/gsbfmp")
	public GenericResponse<Map<String,Object>> getSalesByFieldsGroupByMenuProduct(@RequestBody SearchSalesByFieldsDTO salesDTO) {
		logger.info("OrderDetailController.getSalesByFieldsGroupByMenuProduct()");
		logger.debug("Object: "+salesDTO);
		GenericResponse<Map<String,Object>> response = new GenericResponse<>();

		List<Map<String,Object>> result =orderdetailService.getSalesByFieldsGroupByMenuProduct(salesDTO);
		if(result != null && result.size()>0) {
			response.setDatalist(result);
			response.setResponseMessage("Busqueda realizada con exito");
			response.setFinalTimesTamp(LocalDateTime.now());
			logger.info(HttpStatus.OK);
			
			return response;
		}
			response.setResponseMessage("Error al realizar la peticion");
			response.setResponseCode("1");
			response.setFinalTimesTamp(LocalDateTime.now());
			logger.error(HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
	}
}
	

	

