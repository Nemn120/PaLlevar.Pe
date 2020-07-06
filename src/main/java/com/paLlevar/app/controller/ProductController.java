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

import com.paLlevar.app.model.entities.ProductEntity;
import com.paLlevar.app.model.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	String path = "http://localhost:8080/product";
	
	@Autowired
	private ProductService productService;
	
	
	@GetMapping(path="/glp") // TERMINADO
	public ResponseEntity<List<ProductEntity>>  getListProduct(){
		List<ProductEntity>  lista=productService.getAll();
		return new ResponseEntity<List<ProductEntity>>(lista,HttpStatus.OK);
	}
	// EN CASO DE LISTAR TODO POR ORGANIZACION
	@GetMapping(value="/glpbo/{id}") // TERMINADO
	public ResponseEntity<List<ProductEntity>>  getListProductByOrganizationId(@PathVariable("id")Integer id){
		List<ProductEntity>  lista=productService.getAllProductByCompanyId(id);
		return new ResponseEntity<List<ProductEntity>>(lista,HttpStatus.OK);
	}
	
	// EN CASO DE LISTAR POR ORGANIZCION Y SUCURSAL
	@PostMapping(path="/glpbos") // TERMINADO
	public ResponseEntity<List<ProductEntity>>  getListProductByOrganizationIdAndSucursalId(@RequestBody ProductEntity pro){
		List<ProductEntity>  lista=productService.getAllProductByCompanyIdAndSucursalId(pro);
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
