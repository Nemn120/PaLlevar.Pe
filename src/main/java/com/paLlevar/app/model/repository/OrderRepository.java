package com.paLlevar.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.paLlevar.app.model.entities.OrderEntity;

public interface OrderRepository extends OrderCustomRepository, JpaRepository<OrderEntity, Integer>{
	@Query("SELECT o FROM OrderEntity o WHERE o.organizationId=:organizationId AND o.status=:status AND o.sucursalId=:sucursalId ")
	public List<OrderEntity> getListOrderByStatus(@Param("status") String status, @Param("sucursalId") Integer idSucursal, @Param("organizationId") Integer orgId );
	
	
	List<OrderEntity> findByOrganizationIdAndStatus(Integer organizationId,String status);
	
	//public List<OrderEntity> findByStatusAndSucursalIdAndOrganizationId(String satus, Integer sucursalId, Integer organizationId);
	// TRAE LOS PEDIDOS SEGUN EL ESTADO
	@Query("SELECT o FROM OrderEntity o WHERE  o.userOrder.id=:userId AND o.status not in (:status) ")
	public List<OrderEntity> getListOrderByNotStatusAndUserId(@Param("status") List<String> status, @Param("userId") Integer userId);
	
	@Query("SELECT o FROM OrderEntity o WHERE  o.organizationId=:orgId AND o.status in (:status) ")
	public List<OrderEntity> getListOrderByStatusAndOrgId(@Param("status") List<String> status, @Param("orgId") Integer orgId);
	

	@Modifying
	@Query("UPDATE OrderEntity set status=:status where id=:id")
	void updateOrderStatus(@Param("id") Integer id, @Param("status") String status);
	
	@Modifying
	@Query("UPDATE OrderEntity set phone=:phone, address=:address, reference=:reference where id=:id")
	void updateOrder(@Param("id") Integer id, @Param("phone") String phone, @Param("address") String address, @Param("reference") String reference);
}
