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
}
