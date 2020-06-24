package com.paLlevar.app.model.services.impl;

import java.util.List;

import com.paLlevar.app.model.entities.OrderEntity;
import com.paLlevar.app.model.repository.OrderRepository;
import com.paLlevar.app.model.services.OrderService;

public class OrderServiceImpl implements OrderService {

	private OrderRepository repo;
	
	@Override
	public List<OrderEntity> getAll() {
		return repo.findAll();
	}

	@Override
	public OrderEntity getOneById(Integer id) {
		return repo.getOne(id);
	}

	@Override
	public OrderEntity save(OrderEntity t) {
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

}
