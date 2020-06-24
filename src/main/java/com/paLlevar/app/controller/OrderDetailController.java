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
import com.paLlevar.app.model.services.OrderDetailService;

@RestController
@RequestMapping("/orderdetail")
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
	public OrderDetailEntity saveOrderDetail(@RequestBody OrderDetailEntity orde) {
		OrderDetailEntity orderdetailSave = orderdetailService.save(orde);
		return orderdetailSave;
	}
	
	@DeleteMapping(value="/dod/{id}")
	public void deletedOrderDetail(@PathVariable("id")Integer id) {
		orderdetailService.deleteById(id);
	}
}
