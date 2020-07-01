package com.paLlevar.app.controller;

import java.util.List;

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
	
	@GetMapping(path="/godbsi/{id}/{status}/{org}/{suc}")
	public ResponseEntity<OrderDetailEntity> getOrderDetailByStatusAndId(@PathVariable("id")Integer id, @PathVariable("status")String status,@PathVariable("org")Integer org,@PathVariable("suc")Integer suc){
		OrderDetailEntity orderdetail = new OrderDetailEntity();
		orderdetail = orderdetailService.getOrderDetailByStatusAndId(status,id,org,suc);
		return new ResponseEntity<OrderDetailEntity>(orderdetail,HttpStatus.OK);
	}
	
	@GetMapping(path="/glodbs/{status}/{org}/{suc}")
	public ResponseEntity<List<OrderDetailEntity>>  getListOrderDetailByStatus(@PathVariable("status")String status,@PathVariable("org")Integer org,@PathVariable("suc")Integer suc){
		List<OrderDetailEntity> lista = orderdetailService.getListOrderDetailByStatus(status, org, suc);
		return new ResponseEntity<List<OrderDetailEntity>>(lista,HttpStatus.OK);
		
	}
	
	@GetMapping(path="/glodbsa/{org}/{suc}")
	public ResponseEntity<List<OrderDetailEntity>>  getListOrderDetailAttent(@PathVariable("org")Integer org,@PathVariable("suc")Integer suc){
		List<OrderDetailEntity> lista = orderdetailService.getListOrderDetailByStatus(Constants.ORDER_DETAIL_STATUS_ATTENT, org, suc);
		return new ResponseEntity<List<OrderDetailEntity>>(lista,HttpStatus.OK);
		
	}
	
	@PostMapping(path="/adm/{org}/{suc}")
	public ResponseEntity<Object> assignDeliveryMan(@RequestBody OrderDetailEntity order, @RequestBody UserEntity us,@PathVariable("org") Integer org,
			@PathVariable("suc") Integer suc) {
		orderdetailService.assignDeliveryMan(order.getId(),us.getId(), suc, org);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	
}
