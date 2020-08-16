package com.paLlevar.app.model.repository;
import com.paLlevar.app.model.entities.UserEntity;
import java.util.List;
public interface UserCustomRepository {
	public List<UserEntity> getListUserByOrganization(UserEntity user);
}
