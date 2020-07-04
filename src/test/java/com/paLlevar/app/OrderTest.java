package com.paLlevar.app;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paLlevar.app.model.entities.OrderEntity;
import com.paLlevar.app.model.services.OrderService;
import com.paLlevar.app.util.Constants;

@SpringBootTest
public class OrderTest {

	@Autowired
	private OrderService orderService;
	
	@Test
	void contextLoads() {
		OrderEntity od = new OrderEntity();
		od.setOrganizationId(1);
		od.setSucursalId(1);
		
		List<OrderEntity> orderList = orderService.getListOrderByStatus(Constants.ORDER_STATUS__PENDING,od);
		
		orderList.forEach(x -> System.out.println(x));
		
	}
}
