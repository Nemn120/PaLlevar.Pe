package com.paLlevar.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paLlevar.app.model.entities.MenuDayProductEntity;

public interface MenuDayProductRepository extends JpaRepository<MenuDayProductEntity, Integer>{

	
	@Query("SELECT m FROM MenuDayProductEntity m  WHERE m.menuDay.id=:idMenu AND m.organizationId=:organizationId AND m.sucursalId=:sucursalId")
	List<MenuDayProductEntity> getMenuDayProductByMenuIdAndOrganizationIdAndSucursalId(@Param("idMenu")Integer idMenu,@Param("organizationId") Integer organizationId, @Param("sucursalId")Integer sucursalId);
	@Query("SELECT m FROM MenuDayProductEntity m  WHERE m.menuDay.id=:idMenu AND m.organizationId=:organizationId ")
	List<MenuDayProductEntity> getMenuDayProductByMenuIdAndOrganizationId(@Param("idMenu")Integer idMenu,@Param("organizationId") Integer organizationId);
	@Query("SELECT m FROM MenuDayProductEntity m  WHERE m.menuDay.id=:idMenu") 
	List<MenuDayProductEntity> getListMenuProductByMenuId(@Param("idMenu")Integer idMenu);
	
	MenuDayProductEntity findByIdAndStatus(Integer id, String status);
	
	List<MenuDayProductEntity> findByOrganizationIdAndStatus(Integer organizationId, String status);
	
	List<MenuDayProductEntity> findByOrganizationIdAndStatusAndType(Integer organizationId, String status,String type);
	
	
	@Query(value="SELECT m FROM menu_product_day m INNER JOIN order_detail od ON m.id=od.menu_product_id INNER JOIN order_header oh ON od.order_id=oh.id"
			+ " WHERE m.organization_id=:organizationId AND m.status=:status AND oh.user_order_id=:userId "
			, nativeQuery = true)
	List<MenuDayProductEntity> getFavoriteMenuDayProductByUserAndOrganizationId(@Param("userId")Integer userId,@Param("organizationId") Integer organizationId, @Param("status") String status);
	
	
	@Query("SELECT m FROM MenuDayProductEntity m INNER JOIN m.product pro where m.status=:status and UPPER(pro.name) LIKE CONCAT('%',UPPER(:searchProduct),'%')")
	List<MenuDayProductEntity> getListSearchMenuProduct(@Param("searchProduct")String searchProduct, @Param("status") String status);
	
}

