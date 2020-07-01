package com.paLlevar.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paLlevar.app.model.entities.CategoryProductEntity;
import com.paLlevar.app.model.entities.OrderDetailEntity;
import com.paLlevar.app.model.entities.OrderEntity;
import com.paLlevar.app.model.entities.ProductEntity;
import com.paLlevar.app.model.repository.CategoryProductRepository;
import com.paLlevar.app.model.repository.ProductRepository;
import com.paLlevar.app.model.services.OrderDetailService;
import com.paLlevar.app.model.services.OrderService;
import com.paLlevar.app.model.services.ProductService;
import com.paLlevar.app.util.Constants;


@SpringBootTest
class OrderDetailTest {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryProductRepository categoryProductRepository;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	
	/*@Test
	void test() {
		fail("Not yet implemented");
	}*/
	
	@Test
	void contextLoads() {
		
		
		
		CategoryProductEntity ct = new CategoryProductEntity();
		ct.setDescription("Ensaladas");
		ct.setName("Ensaladas");
		CategoryProductEntity ct1 =categoryProductRepository.save(ct);
		
		assertThat(ct1.equals(ct));
		
		
		ProductEntity product1= new ProductEntity();
		product1.setDescription("Ensalada de palta");
		product1.setName("Ensalada de palta");
		product1.setCategoryProduct(ct1);
		ProductEntity pr = productService.save(product1);
		assertThat(product1.getDescription().equals(pr.getDescription()));
		
		ProductEntity product2= new ProductEntity();
		product2.setDescription("Ensalada de frutas");
		product2.setName("Ensalada de frutas");
		product2.setCategoryProduct(ct1);
		ProductEntity pr2 = productService.save(product2);
		assertThat(product2.getDescription().equals(pr2.getDescription()));
		
		ProductEntity product3= new ProductEntity();
		product3.setDescription("Ensalada de papa");
		product3.setName("Ensalada de papa");
		product3.setCategoryProduct(ct1);
		ProductEntity pr3 = productService.save(product3);
		assertThat(product3.getDescription().equals(pr3.getDescription()));
		
		OrderEntity order1 = new OrderEntity();
		order1.setStatus(Constants.ORDER_STATUS__PENDING);
		order1.setOrganizationId(1);
		order1.setSucursalId(1);
		orderService.save(order1);
		OrderDetailEntity orderdetail1 = new OrderDetailEntity();
		orderdetail1.setProduct(product1);
		orderdetail1.setPrice(12.0);
		orderdetail1.setStatus(Constants.ORDER_DETAIL_STATUS_PENDING);
		orderdetail1.setOrganizationId(1);
		orderdetail1.setSucursalId(1);
		orderdetail1.setOrder(order1);
		orderDetailService.save(orderdetail1);
		OrderDetailEntity orderdetail2 = new OrderDetailEntity();
		orderdetail2.setProduct(product2);
		orderdetail2.setPrice(10.0);
		orderdetail2.setStatus(Constants.ORDER_DETAIL_STATUS_PENDING);
		orderdetail2.setOrganizationId(1);
		orderdetail2.setSucursalId(1);
		orderdetail2.setOrder(order1);
		orderDetailService.save(orderdetail2);
		OrderDetailEntity orderdetail3 = new OrderDetailEntity();
		orderdetail3.setProduct(product3);
		orderdetail3.setPrice(11.0);
		orderdetail3.setStatus(Constants.ORDER_DETAIL_STATUS_PENDING);
		orderdetail3.setOrganizationId(1);
		orderdetail3.setSucursalId(1);
		orderdetail3.setOrder(order1);
		orderDetailService.save(orderdetail3);
		
		assertThat(orderService.CheckOrder(1, 1, 1));
		
		assertThat((orderDetailService.getOrderDetailByStatusAndId(Constants.ORDER_DETAIL_STATUS_PENDING,orderdetail1.getId() ,1, 1).getProduct().getDescription()).equals(orderdetail1.getProduct().getDescription()));
		
	}

}
