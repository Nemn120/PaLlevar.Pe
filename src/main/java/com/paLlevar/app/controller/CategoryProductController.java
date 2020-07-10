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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.paLlevar.app.model.entities.CategoryProductEntity;
import com.paLlevar.app.model.entities.CompanyEntity;
import com.paLlevar.app.model.entities.ProductEntity;
import com.paLlevar.app.model.services.CategoryProductService;

@RestController
@RequestMapping("/categoryproduct")
public class CategoryProductController {
	String path = "http://localhost:8080/categoryproduct";
	
	@Autowired
	private CategoryProductService categoryProductService;
	
	
	@GetMapping(path="/glcp")
	public ResponseEntity<List<CategoryProductEntity>>  getListCategoryProduct(){
		List<CategoryProductEntity>  lista=categoryProductService.getAll();
		return new ResponseEntity<List<CategoryProductEntity>>(lista,HttpStatus.OK);
	}
	
	@GetMapping(path="/glcpbo/{id}")
	public ResponseEntity<List<CategoryProductEntity>>  getListCategoryProductByOrgId(@PathVariable("id") Integer id){
		List<CategoryProductEntity>  lista=categoryProductService.getListCategoryProductByOrgId(id);
		return new ResponseEntity<List<CategoryProductEntity>>(lista,HttpStatus.OK);
	}
	
	@PostMapping(path="/scp")
	public CategoryProductEntity saveCategoryProduct(@RequestPart("category") CategoryProductEntity pr, @RequestPart("file") MultipartFile file) throws IOException{
		if(file.getBytes().length >0)
			pr.setPhoto(file.getBytes());
		CategoryProductEntity categoryProductEntity = categoryProductService.save(pr);
		return categoryProductEntity;
	}
	@GetMapping(value = "/gp/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> getPhotoById(@PathVariable("id") Integer id) {
		CategoryProductEntity c = categoryProductService.getOneById(id);
		 byte[]	data = c.getPhoto();
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}
	
	
	@DeleteMapping(value="/dcp/{id}")
	public void deletedCategoryProduct(@PathVariable("id")Integer id) {
		categoryProductService.deleteById(id);
	}
}
