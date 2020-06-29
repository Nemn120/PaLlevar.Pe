package com.paLlevar.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paLlevar.app.model.entities.CategoryProductEntity;
import com.paLlevar.app.model.entities.OrderDetailEntity;
import com.paLlevar.app.model.entities.OrderEntity;
import com.paLlevar.app.model.entities.ProductEntity;
import com.paLlevar.app.model.entities.UserEntity;
import com.paLlevar.app.model.repository.CategoryProductRepository;
import com.paLlevar.app.model.repository.ProductRepository;
import com.paLlevar.app.model.repository.UserRepository;
import com.paLlevar.app.model.services.ProductService;
import com.paLlevar.app.model.services.UserService;

@SpringBootTest
class PaLlevarBackendApplicationTests {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryProductRepository categoryProductRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	
	@Test
	void contextLoads() {
		
		UserEntity us1 = new UserEntity();
		
		us1.setUsername("email3");
		us1.setPassword("123");
		UserEntity user2 =userService.registerUserByProfile(us1);
		assertThat(user2.equals(us1));
		
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
		/*ProductEntity product1= new ProductEntity();
		product1.setDescription("Ensalada de papa");
		product1.setName("Ensalada de papa");
		ProductEntity pr = productService.save(product1);
		assertThat(product1.getDescription().equals(pr.getDescription()));
		
		// SIMULA
		List<ProductEntity> proList = new ArrayList<ProductEntity>();
		
		
		OrderDetailEntity o1 = new OrderDetailEntity();
		o1.setStatus("1");
		OrderDetailEntity o2 = new OrderDetailEntity();
		o1.setStatus("2");
		OrderDetailEntity o3 = new OrderDetailEntity();
		o1.setStatus("3");
		OrderDetailEntity o4 = new OrderDetailEntity();
		o1.setStatus("1");
		OrderDetailEntity o5 = new OrderDetailEntity();
		o1.setStatus("1");
	

		
		OrderDetailEntity od1 = new OrderDetailEntity();
	
		*/
		
	}
	
	/*@Test
	void contextLoads2() {
		
		
		
		ProductEntity product1= new ProductEntity();
		product1.setDescription("Ensalada de papa");
		product1.setName("Ensalada de papa");
		ProductEntity pr = productService.save(product1);
		assertThat(product1.getDescription().equals(pr.getDescription()));
		
		// SIMULA
		List<ProductEntity> proList = new ArrayList<ProductEntity>();
		
		
		OrderDetailEntity o1 = new OrderDetailEntity();
		o1.setStatus("1");
		OrderDetailEntity o2 = new OrderDetailEntity();
		o1.setStatus("2");
		OrderDetailEntity o3 = new OrderDetailEntity();
		o1.setStatus("3");
		OrderDetailEntity o4 = new OrderDetailEntity();
		o1.setStatus("1");
		OrderDetailEntity o5 = new OrderDetailEntity();
		o1.setStatus("1");
	

		
		OrderDetailEntity od1 = new OrderDetailEntity();
		
		
		
		
		
	}
	*/
	

}
