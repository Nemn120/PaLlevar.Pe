package com.paLlevar.app.model.repository;

import java.util.List;

import com.paLlevar.app.model.entities.OrderDetailEntity;
import com.paLlevar.app.model.services.dto.SearchSalesByFieldsDTO;

public interface OrderDetailCustomRepository {
	// metodos customizados
	
	public List<Object[]> getOrderDetailListByCriterial(OrderDetailEntity od);
	
	public List<Object[]> getSalesByFieldsGroupByMenuProduct(SearchSalesByFieldsDTO ssbf);
}
