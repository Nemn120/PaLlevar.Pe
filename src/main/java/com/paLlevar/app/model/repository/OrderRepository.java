package com.paLlevar.app.model.repository;

import java.util.List;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.paLlevar.app.model.entities.OrderEntity;

public interface OrderRepository extends OrderCustomRepository, JpaRepository<OrderEntity, Integer>{
	@Query("SELECT o FROM OrderEntity o WHERE o.organizationId=:organizationId AND o.status=:status AND o.sucursalId=:sucursalId ")
	public List<OrderEntity> getListOrderByStatus(@Param("status") String status, @Param("sucursalId") Integer idSucursal, @Param("organizationId") Integer orgId );
	
	List<OrderEntity> findByOrganizationIdAndStatus(Integer organizationId,String status);
	
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

	@Query("SELECT SUM(o.total) FROM OrderEntity o WHERE  o.organizationId=:orgId AND o.createDate BETWEEN :initDate AND :finalDate ")
	public Double getSales(@Param("orgId") Integer orgId, @Param("initDate") LocalDateTime initDate, @Param("finalDate") LocalDateTime finalDate);

	@Query("SELECT SUM(o.quantity) FROM OrderEntity o WHERE  o.organizationId=:orgId AND o.createDate BETWEEN :initDate AND :finalDate ")
	public Integer getQuantity(@Param("orgId") Integer orgId, @Param("initDate") LocalDateTime initDate, @Param("finalDate") LocalDateTime finalDate);

	@Query("SELECT o FROM OrderEntity o WHERE o.organizationId=:organizationId AND o.status=:status ORDER BY o.createDate DESC ")
	public List<OrderEntity> getListOrderRecentByStatus(@Param("status") String status, @Param("organizationId") Integer orgId );
	
	@Modifying
	@Query("UPDATE OrderEntity set status=:status, delivered_date=now() where id =:id")
	void updateConfirmOrderById(@Param("id") Integer id, @Param("status") String status);
	
}
