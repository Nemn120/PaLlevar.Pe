package com.paLlevar.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paLlevar.app.model.entities.ProductEntity;
import com.paLlevar.app.model.services.ProductService;
import com.paLlevar.app.security.UserPrincipal;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	String path = "http://localhost:8080/product";
	
	@Autowired
	private ProductService productService;
	
	
	@GetMapping(path="/glp")
	public ResponseEntity<List<ProductEntity>>  getListProduct(){
		List<ProductEntity>  lista=productService.getAll();
		return new ResponseEntity<List<ProductEntity>>(lista,HttpStatus.OK);
	}
	@PostMapping(path="/sp")
	public ProductEntity saveProduct(@RequestBody ProductEntity pr){
		ProductEntity productSave = productService.save(pr);
		return productSave;
	}
	@DeleteMapping(value="/dp/{id}")
	public void deletedProduct(@PathVariable("id")Integer id) {
		productService.deleteById(id);
	}
	
	
	
}
