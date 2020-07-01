package com.paLlevar.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.paLlevar.app.model.entities.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer>{
	@Query("SELECT o FROM OrderEntity o WHERE o.organizationId=:organizationId AND o.status=:status AND o.sucursalId=:sucursalId ")
	public List<OrderEntity> getListOrderByStatus(@Param("status") String status, @Param("sucursalId") Integer idSucursal, @Param("organizationId") Integer orgId );

}
