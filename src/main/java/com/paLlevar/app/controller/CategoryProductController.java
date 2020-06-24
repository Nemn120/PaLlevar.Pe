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
import com.paLlevar.app.model.entities.CategoryProductEntity;
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
	@PostMapping(path="/scp")
	public CategoryProductEntity saveCategoryProduct(@RequestBody CategoryProductEntity cpe){
		CategoryProductEntity categoryProductEntity = categoryProductService.save(cpe);
		return categoryProductEntity;
	}
	@DeleteMapping(value="/dcp/{id}")
	public void deletedCategoryProduct(@PathVariable("id")Integer id) {
		categoryProductService.deleteById(id);
	}
}
