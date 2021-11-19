package com.paLlevar.app.model.services.mapper;

import com.paLlevar.app.model.dto.UserDTO;
import com.paLlevar.app.model.entities.UserEntity;

import java.util.List;

public interface UserMapper {

    UserEntity mapIn(UserDTO userDTO);

    UserDTO mapOut(UserEntity userEntity);

    List<UserEntity> mapIn(List<UserDTO> userDTO);

    List<UserDTO> mapOut(List<UserEntity> userEntity);
}
