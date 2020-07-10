package com.paLlevar.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paLlevar.app.model.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	UserEntity findOneByUsername(String username);
	
	//UserEntity 
	
	@Query("SELECT u FROM UserEntity u WHERE u.id=:id AND u.organizationId=:organizationId AND u.sucursalId=:sucursalId")
	public UserEntity getUserbyOrganitationDyIDBySucursal(@Param("id") Integer id,@Param("sucursalId") Integer idSucursal, @Param("organizationId") Integer orgId);
	
	
	@Query("SELECT u FROM UserEntity u WHERE u.status=:status AND u.organizationId=:organizationId AND u.sucursalId=:sucursalId AND u.profile.idProfile=:idProfile")
	public List<UserEntity> getUserListByStatusAndProfileID(@Param("status") String status,@Param("sucursalId") Integer idSucursal, 
			@Param("organizationId") Integer orgId, @Param("idProfile") Integer idperfil);
	
	@Modifying
	@Query("UPDATE UserEntity set photo =:photo where id =:id")
	void updatePhoto(@Param("id") Integer id, @Param("photo") byte[] photo);
	
}
