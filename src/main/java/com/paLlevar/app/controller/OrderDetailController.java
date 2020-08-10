package com.paLlevar.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Autowired
	private OrderDetailService orderdetailService;

	@GetMapping(path="/glod")
	public ResponseEntity<List<OrderDetailEntity>>  getListOrderDetail(){
		List<OrderDetailEntity> lista = orderdetailService.getAll();
		return new ResponseEntity<List<OrderDetailEntity>>(lista,HttpStatus.OK);
		
	}
	
	@PostMapping(path="/sod")
	public OrderDetailEntity saveOrderDetail(@RequestBody OrderDetailEntity order) {
		OrderDetailEntity orderdetailSave = orderdetailService.save(order);
		return orderdetailSave;
	}
	
	@DeleteMapping(value="/dod/{id}")
	public void deletedOrderDetail(@PathVariable("id")Integer id) {
		orderdetailService.deleteById(id);
	}
	
	
	@PostMapping(path="/saod")
	public OrderDetailEntity saveAttendOrderDetail(@RequestBody OrderDetailEntity order) {
		order.setStatus(Constants.ORDER_DETAIL_STATUS_ATTENT);
		orderdetailService.saveAttendOrderDetail(order);
		return null;
	}
	
	@PostMapping(path="/sdod")
	public OrderDetailEntity saveDeliveryOrderDetail(@RequestBody OrderDetailEntity order) {
		order.setStatus(Constants.ORDER_DETAIL_STATUS_DELIVERY);
		orderdetailService.saveAttendOrderDetail(order);
		return null;
	}
	
	
	@PostMapping(path="/glodbo") // PENDIENTE DE CORRECCION
	public ResponseEntity<List<OrderDetailEntity>>  getListOrderDetailByOrderId(@RequestBody OrderEntity order){
		List<OrderDetailEntity> lista = orderdetailService.getListOrderDetailByOrderId(order.getId(), order.getOrganizationId());
		return new ResponseEntity<List<OrderDetailEntity>>(lista,HttpStatus.OK);
		
	}
	
	@PostMapping(path="/admo")
	public ResponseEntity<Object> assignDeliveryManToOrder(@RequestBody OrderDetailEntity order) {
		orderdetailService.save(order);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	@PostMapping(path="/gsbfmp")
	public ResponseEntity<?> getSalesByFieldsGroupByMenuProduct(@RequestBody SearchSalesByFieldsDTO salesDTO) {
		Map<String,Object> response = new HashMap<>();

		try {
			response.put("data", orderdetailService.getSalesByFieldsGroupByMenuProduct(salesDTO));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}catch(Exception e) {
			response.put(Constants.MESSAGE_BODY_RESPONSE, "Error al realizar la peticion");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	
}
