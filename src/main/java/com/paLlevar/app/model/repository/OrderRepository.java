package com.paLlevar.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.paLlevar.app.model.entities.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer>{
	@Query("SELECT o FROM OrderEntity o WHERE o.organizationId=:organizationId AND o.status=:status AND o.sucursalId=:sucursalId ")
	public List<OrderEntity> getListOrderByStatus(@Param("status") String status, @Param("sucursalId") Integer idSucursal, @Param("organizationId") Integer orgId );
	
	// TRAE LOS PEDIDOS SEGUN EL ESTADO
	@Query("SELECT o FROM OrderEntity o WHERE  o.userOrder.id=:userId AND o.status not in (:status) ")
	public List<OrderEntity> getListOrderByNotStatusAndUserId(@Param("status") List<String> status, @Param("userId") Integer userId);

}
