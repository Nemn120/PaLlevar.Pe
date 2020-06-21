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

import com.paLlevar.app.model.entities.Product;
import com.paLlevar.app.model.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	String path = "http://localhost:8080/product";
	
	@Autowired
	private ProductService productService;
	
	
	@GetMapping(path="/glp")
	public ResponseEntity<List<Product>>  getListProduct(){
		List<Product>  lista=productService.getAll();
		return new ResponseEntity<List<Product>>(lista,HttpStatus.OK);
	}
	@PostMapping(path="/sp")
	public Product saveProduct(@RequestBody Product pr){
		Product productSave = productService.save(pr);
		return productSave;
	}
	@DeleteMapping(value="/dp/{id}")
	public void deletedProduct(@PathVariable("id")Integer id) {
		productService.deleteById(id);
	}
	
}
