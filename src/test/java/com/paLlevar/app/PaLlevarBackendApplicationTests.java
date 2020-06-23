package com.paLlevar.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paLlevar.app.model.entities.CategoryProductEntity;
import com.paLlevar.app.model.entities.ProductEntity;
import com.paLlevar.app.model.repository.CategoryProductRepository;
import com.paLlevar.app.model.repository.ProductRepository;
import com.paLlevar.app.model.services.ProductService;

@SpringBootTest
class PaLlevarBackendApplicationTests {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryProductRepository categoryProductRepository;
	
	@Autowired
	private ProductService productService;
	@Test
	void contextLoads() {
		/*CategoryProduct ct = new CategoryP/roduct();
		ct.setDescription("Ensaladas");
		ct.setName("Ensaladas");
		CategoryProduct ct1 =categoryProductRepository.save(ct);
		
		assertThat(ct1.equals(ct));
		
		
		Product product1= new Product();
		product1.setDescription("Ensalada de palta");
		product1.setName("Ensalada de palta");
		product1.setCategoryProduct(ct1);
		Product pr = productRepository.save(product1);
		assertThat(product1.getDescription().equals(pr.getDescription()));
		*/
		ProductEntity product1= new ProductEntity();
		product1.setDescription("Ensalada de papa");
		product1.setName("Ensalada de papa");
		ProductEntity pr = productService.save(product1);
		assertThat(product1.getDescription().equals(pr.getDescription()));
	}

}
