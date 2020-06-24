package com.paLlevar.app.model.services.impl;

import java.util.List;

import com.paLlevar.app.model.entities.OrderDetailEntity;
import com.paLlevar.app.model.repository.OrderDetailRepository;
import com.paLlevar.app.model.services.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService {
	
	private OrderDetailRepository repo;

	@Override
	public List<OrderDetailEntity> getAll() {
		return repo.findAll();
	}

	@Override
	public OrderDetailEntity getOneById(Integer id) {
		return repo.getOne(id);
	}

	@Override
	public OrderDetailEntity save(OrderDetailEntity t) {
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

}
