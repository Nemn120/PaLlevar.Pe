package com.paLlevar.app.model.repository;

import java.util.List;

import com.paLlevar.app.model.entities.OrderEntity;
import com.paLlevar.app.model.services.dto.SearchOrderByDeliveryManDTO;
import com.paLlevar.app.model.services.dto.SearchOrderByFieldsDTO;

public interface OrderCustomRepository {

	public List<OrderEntity> getOrderListByDeliveryId(SearchOrderByDeliveryManDTO sobd);
	
	public List<OrderEntity> getOrderListByFields(SearchOrderByFieldsDTO sobf);
	
	public List<OrderEntity> getListOrderRecentByStatusLimitedTo(Integer limit, String status, Integer id);
}
