package com.paLlevar.app.model.services;

import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.ClientEntity;

@Service
public interface ClientService extends GenericCRUD<ClientEntity, Integer>{

}
