package com.paLlevar.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paLlevar.app.model.entities.OrderDetailEntity;

public interface OrderDetailRepository extends OrderDetailCustomRepository, JpaRepository<OrderDetailEntity, Integer>{
	
	@Query("SELECT o FROM OrderDetailEntity o WHERE o.status=:status AND o.id=:id AND o.organizationId=:organizationId")
	public OrderDetailEntity getOrderDetailByStatusAndId(@Param("status") String status, @Param("id") Integer id, @Param("organizationId") Integer Idorganization);
	
	@Query("SELECT o FROM OrderDetailEntity o WHERE o.status=:status AND o.organizationId=:organizationId")
	public List<OrderDetailEntity> getListOrderDetailByStatus(@Param("status") String status, @Param("organizationId") Integer orgId);
	
	@Query("SELECT o FROM OrderDetailEntity o WHERE o.order.id=:id AND o.organizationId=:organizationId")
	public List<OrderDetailEntity> getListOrderDetailByOrderId(@Param("id") Integer oid, @Param("organizationId") Integer orgId);

	@Query("SELECT o FROM OrderDetailEntity o WHERE o.id=:id AND o.organizationId=:organizationId")
	public OrderDetailEntity getOrderDetailById(@Param("id") Integer id, @Param("organizationId") Integer orgId);
	@Modifying
	@Query("UPDATE OrderDetailEntity set status=:status where order_id=:id")
	void updateOrderDetailStatus(@Param("id") Integer id, @Param("status") String status);
}
