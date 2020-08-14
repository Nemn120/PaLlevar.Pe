package com.paLlevar.app.controller;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paLlevar.app.model.entities.CategoryProductEntity;
import com.paLlevar.app.model.services.CategoryProductService;

@RestController
@RequestMapping("/categoryproduct")
public class CategoryProductController {
	String path = "http://localhost:8080/categoryproduct";
	
	private static final Logger logger = LogManager.getLogger(CategoryProductController.class);	
	
	@Autowired
	private CategoryProductService categoryProductService;
	
	@GetMapping(path="/glcp")
	public ResponseEntity<List<CategoryProductEntity>>  getListCategoryProduct(){
		logger.info("CategoryProductController.getListCategoryProduct()");
		List<CategoryProductEntity>  lista=categoryProductService.getAll();
		return new ResponseEntity<List<CategoryProductEntity>>(lista,HttpStatus.OK);
	}
	
	@GetMapping(path="/glcpbo/{id}")
	public ResponseEntity<List<CategoryProductEntity>>  getListCategoryProductByOrgId(@PathVariable("id") Integer id){
		logger.info("CategoryProductController.getListCategoryProductByOrgId()");
		List<CategoryProductEntity>  lista=categoryProductService.getListCategoryProductByOrgId(id);
		return new ResponseEntity<List<CategoryProductEntity>>(lista,HttpStatus.OK);
	}
	
	@PostMapping(path="/scp")
	public CategoryProductEntity saveCategoryProduct(@RequestPart("category") CategoryProductEntity pr, @RequestPart("file") MultipartFile file) throws IOException{
		logger.info("CategoryProductController.saveCategoryProduct()");
		if(file.getBytes().length >0)
			pr.setPhoto(file.getBytes());
		else
			logger.warn("Categoria de producto: "+ pr.getName() + "no contiene foto");
		CategoryProductEntity categoryProductEntity = categoryProductService.save(pr);
		return categoryProductEntity;
	}
	@GetMapping(value = "/gp/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> getPhotoById(@PathVariable("id") Integer id) {
		logger.info("CategoryProductController.getPhotoById()");
		CategoryProductEntity c = categoryProductService.getOneById(id);
		 byte[]	data = c.getPhoto();
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/dcp/{id}")
	public void deletedCategoryProduct(@PathVariable("id")Integer id) {
		logger.info("CategoryProductController.deletedCategoryProduct()");
		categoryProductService.deleteById(id);
	}
}
