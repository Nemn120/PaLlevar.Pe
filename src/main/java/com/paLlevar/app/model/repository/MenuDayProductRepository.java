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

	@Query("SELECT m FROM MenuDayProductEntity m INNER JOIN m.product pro where m.status=:status and UPPER(pro.name) LIKE CONCAT('%',UPPER(:searchProduct),'%') order by m.organizationId asc")
	List<MenuDayProductEntity> getListSearchMenuProduct(@Param("searchProduct")String searchProduct, @Param("status") String status);

	@Query(value="SELECT DISTINCT * FROM menu_product_day INNER JOIN order_detail  ON order_detail.menu_product_id = menu_product_day.id INNER JOIN order_header ON order_header.id=order_detail.order_id  "
			+ "WHERE menu_product_day.organization_id=:organizationId AND menu_product_day.status=:status AND order_header.user_order_id=:userId", nativeQuery = true)
	List<MenuDayProductEntity> getListFavoriteMenuDayProductByUserAndOrganizationId(@Param("organizationId") Integer organizationId, @Param("status") String status, @Param("userId")Integer userId);

}

