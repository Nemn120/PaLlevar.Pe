package com.paLlevar.app.model.repository;

import java.util.List;
import com.paLlevar.app.model.services.dto.SearchOrderByDeliveryManDTO;

public interface OrderCustomRepository {

	public List<Object[]> getOrderListByDeliveyId(SearchOrderByDeliveryManDTO sobd);
}
