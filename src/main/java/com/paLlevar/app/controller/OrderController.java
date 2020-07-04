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
	
	@PostMapping(path="/sobos")
	public OrderEntity saveNewOrderByOrganizationIdAndSucursalId(@RequestBody OrderEntity or) {
		orderService.saveOrderByOrganizationIdAndSucursalId(or);
		return null;
	}
	
	@PostMapping(path="/glop")
	public ResponseEntity<List<OrderEntity>>  getListOrderPendding(@RequestBody OrderEntity or){
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_STATUS__PENDING,or.getOrganizationId(),or.getSucursalId());
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
		
	}
	
	@PostMapping(path="/glod")
	public ResponseEntity<List<OrderEntity>>  getListOrderDelivery(@RequestBody OrderEntity or){
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_STATUS_DELIVERY,or.getOrganizationId(),or.getSucursalId());
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
		
	}
	
	@PostMapping(path="/cho")
	public ResponseEntity<Object>CheckOrder(@RequestBody OrderEntity or){
		Boolean verificar= orderService.CheckOrder(or.getId(), or.getOrganizationId(), or.getSucursalId());
		if(verificar)
			return new ResponseEntity<Object>(HttpStatus.OK);
		else 
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}
	

	@PostMapping(path="/gloa")
	public ResponseEntity<List<OrderEntity>> getListOrderAttent(@RequestBody OrderEntity or){
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_STATUS_ATTENT, or.getOrganizationId(), or.getSucursalId());
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
	}
	/*@GetMapping(path="/glody/{org}/{suc}")
	public ResponseEntity<List<OrderEntity>>  getListOrderDelivery(@PathVariable("org") Integer org,@PathVariable("suc") Integer suc){
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_STATUS_DELIVERY,org,suc);
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);	
	}
	
	/*@GetMapping(path="/gloc/{org}/{suc}")
	public ResponseEntity<List<OrderEntity>> getListOrderCancel(@PathVariable("org") Integer org,@PathVariable("suc") Integer suc){
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_STATUS_CANCEL,org,suc);
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
	}

	@GetMapping(path="/glopr/{org}/{suc}")
	public ResponseEntity<List<OrderEntity>> getListOrderProcess(@PathVariable("org") Integer org,@PathVariable("suc") Integer suc){
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_STATUS_PROCESS,org,suc);
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
	}
	@GetMapping(path="/glof/{org}/{suc}")
	public ResponseEntity<List<OrderEntity>> getListOrderFinaly(@PathVariable("org") Integer org,@PathVariable("suc") Integer suc){
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_STATUS_FINALY, org, suc);
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
	}
	@GetMapping(path="/gloe/{org}/{suc}")
	public ResponseEntity<List<OrderEntity>> getListOrderError(@PathVariable("org") Integer org,@PathVariable("suc") Integer suc){
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_STATUS_ERROR, org, suc);
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
	}
	
	@GetMapping(path="/glod/{org}/{suc}")
	public ResponseEntity<List<OrderEntity>>  getListOrderDelivered(@PathVariable("org") Integer org,@PathVariable("suc") Integer suc){
		List<OrderEntity> lista = orderService.getListOrderByStatus(Constants.ORDER_DETAIL_STATUS_DELIVERED,org,suc);
		return new ResponseEntity<List<OrderEntity>>(lista,HttpStatus.OK);
		
	}
	*/


	
	
}
