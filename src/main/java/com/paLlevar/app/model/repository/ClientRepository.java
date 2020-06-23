package com.paLlevar.app.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paLlevar.app.model.entities.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer>{

}
