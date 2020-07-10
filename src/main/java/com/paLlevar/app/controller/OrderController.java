package com.paLlevar.app.controller;

import java.util.Arrays;
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

import com.paLlevar.app.model.entities.OrderEntity;
import com.paLlevar.app.model.services.OrderService;
import com.paLlevar.app.util.Constants;

@RestController
@RequestMapping("/order")
public class OrderController {

	String path = "http://localhost:8080/order";

	
	@Autowired
	private OrderService orderService;

	@GetMapping(path="/glo")
	public ResponseEntity<List<OrderEntity>>  getListOrder(){
		List<OrderEntity> lista = orderService.getAll();
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
		
	}
	
	@PostMapping(path="/so")
	public OrderEntity saveOrder(@RequestBody OrderEntity or) {
		OrderEntity orderSave = orderService.save(or);
		return orderSave;
	}
	
	@DeleteMapping(value="/do/{id}")
	public void deletedOrder(@PathVariable("id")Integer id) {
		orderService.deleteById(id);
	}
	
	@GetMapping(value="/globu/{id}")
	public ResponseEntity<List<OrderEntity>>  getListOrderByUserIdAndNotStatus(@PathVariable("id")Integer id){
		List<String> statusList = Arrays.asList(Constants.ORDER_DETAIL_STATUS_CANCEL,Constants.ORDER_STATUS_DELIVERED);
		List<OrderEntity> lista = orderService.getListOrderByNotStatusAndUserId(statusList, id);
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
		
	}
	
	@PostMapping(path="/sobos")
	public OrderEntity saveNewOrderByOrganizationId(@RequestBody OrderEntity or) {
		orderService.saveOrderByOrganizationIdAndSucursalId(or);
		return null;
	}
	@PostMapping(path="/cho")
	public ResponseEntity<Object>CheckOrder(@RequestBody OrderEntity or){
		Boolean verificar= orderService.CheckOrder(or.getId(), or.getOrganizationId());
		if(verificar)
			return new ResponseEntity<Object>(HttpStatus.OK);
		else 
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(path="/gloa")
	public ResponseEntity<List<OrderEntity>> getListOrderAttent(@RequestBody OrderEntity or){
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_STATUS_ATTENT, or);
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
	}
	
	
	@PostMapping(path="/glop")
	public ResponseEntity<List<OrderEntity>>  getListOrderPendding(@RequestBody OrderEntity or){
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_STATUS__PENDING,or);
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
		
	}
	
	@PostMapping(path="/glody")
	public ResponseEntity<List<OrderEntity>>  getListOrderDelivery(@RequestBody OrderEntity or){
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_STATUS_DELIVERY,or);
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
		
	}
	
	@PostMapping(path="/gloc")
	public ResponseEntity<List<OrderEntity>> getListOrderCancel(@RequestBody OrderEntity or){
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_STATUS_CANCEL,or);
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
	}

	@PostMapping(path="/glopr")
	public ResponseEntity<List<OrderEntity>> getListOrderProcess(@RequestBody OrderEntity or){
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_STATUS_PROCESS,or);
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
	}

	@PostMapping(path="/gloe")
	public ResponseEntity<List<OrderEntity>> getListOrderError(@RequestBody OrderEntity or){
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_STATUS_ERROR, or);
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
	}
	
	@PostMapping(path="/glod")
	public ResponseEntity<List<OrderEntity>>  getListOrderDelivered(@RequestBody OrderEntity or){
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_STATUS_DELIVERED,or);
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
		
	}
	

	
	
}
