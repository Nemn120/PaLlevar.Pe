package com.paLlevar.app.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paLlevar.app.model.entities.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer>{

}
