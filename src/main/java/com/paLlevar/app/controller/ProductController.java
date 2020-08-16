package com.paLlevar.app.controller;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.paLlevar.app.model.entities.ProductEntity;
import com.paLlevar.app.model.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	String path = "http://localhost:8080/product";
	private static final Logger logger = LogManager.getLogger(ProductController.class);	
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(path="/glp") 
	public ResponseEntity<List<ProductEntity>>  getListProduct(){
		logger.info("ProductController.getListProduct()");
		List<ProductEntity>  lista=productService.getAll();
		return new ResponseEntity<List<ProductEntity>>(lista,HttpStatus.OK);
	}

	@GetMapping(value="/glpbo/{id}") 
	public ResponseEntity<List<ProductEntity>>  getListProductByOrganizationId(@PathVariable("id")Integer id){
		logger.info("ProductController.getListProductByOrganizationId()");
		List<ProductEntity>  lista=productService.getAllProductByCompanyId(id);
		return new ResponseEntity<List<ProductEntity>>(lista,HttpStatus.OK);
	}
	

	@PostMapping(path="/glpbos") 
	public ResponseEntity<List<ProductEntity>>  getListProductByOrganizationIdAndSucursalId(@RequestBody ProductEntity pro){
		logger.info("ProductController.getListProductByOrganizationIdAndSucursalId()");
		List<ProductEntity>  lista=productService.getAllProductByCompanyIdAndSucursalId(pro);		
		return new ResponseEntity<List<ProductEntity>>(lista,HttpStatus.OK);
	}
	
	@GetMapping(value = "/gp/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> getPhoto(@PathVariable("id") Integer id) {
		ProductEntity c = productService.getOneById(id);
		 byte[]	data = c.getPhoto();
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}

	@PostMapping(path="/sp")
	public ProductEntity saveProduct(@RequestPart("product") ProductEntity pr, @RequestPart("file") MultipartFile file) throws IOException{
		logger.info("ProductController.saveProduct()");
		if(file.getBytes().length >0)
			pr.setPhoto(file.getBytes());
		ProductEntity productSave = productService.save(pr);
		return productSave;
	}
	@DeleteMapping(value="/dp/{id}")
	public void deletedProduct(@PathVariable("id")Integer id) {
		logger.info("ProductController.deletedProduct()");

		productService.deleteById(id);
	}
	
	
}
