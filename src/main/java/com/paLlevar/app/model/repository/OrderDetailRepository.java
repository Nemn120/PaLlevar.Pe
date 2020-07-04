package com.paLlevar.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paLlevar.app.model.entities.OrderDetailEntity;
import com.paLlevar.app.model.entities.UserEntity;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer>{
	
	@Query("SELECT o FROM OrderDetailEntity o WHERE o.status=:status AND o.id=:id AND o.organizationId=:organizationId AND o.sucursalId=:sucursalId")
	public OrderDetailEntity getOrderDetailByStatusAndId(@Param("status") String status, @Param("id") Integer id, @Param("organizationId") Integer Idorganization,@Param("sucursalId") Integer idSucursal );
	
	@Query("SELECT o FROM OrderDetailEntity o WHERE o.status=:status AND o.organizationId=:organizationId AND o.sucursalId=:sucursalId")
	public List<OrderDetailEntity> getListOrderDetailByStatus(@Param("status") String status, @Param("organizationId") Integer orgId,@Param("sucursalId") Integer idSucursal );
	
	@Query("SELECT o FROM OrderDetailEntity o WHERE o.order.id=:id AND o.organizationId=:organizationId AND o.sucursalId=:sucursalId")
	public List<OrderDetailEntity> getListOrderDetailByOrderId(@Param("id") Integer oid, @Param("organizationId") Integer orgId,@Param("sucursalId") Integer sucursalId );

	@Query("SELECT o FROM OrderDetailEntity o WHERE o.id=:id AND o.organizationId=:organizationId AND o.sucursalId=:sucursalId")
	public OrderDetailEntity getOrderDetailById(@Param("id") Integer id,@Param("sucursalId") Integer idSucursal, @Param("organizationId") Integer orgId);
	
	
}
