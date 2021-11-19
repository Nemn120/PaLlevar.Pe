package com.paLlevar.app.model.services.mapper.impl;

import com.paLlevar.app.model.dto.ProfileDTO;
import com.paLlevar.app.model.dto.UserDTO;
import com.paLlevar.app.model.entities.ProfileEntity;
import com.paLlevar.app.model.entities.UserEntity;
import com.paLlevar.app.model.services.mapper.UserMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity mapIn(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO,userEntity);
        userEntity.setProfile(mapInProfile(userDTO.getProfile()));
        return userEntity;
    }

    private ProfileEntity mapInProfile(ProfileDTO profile) {
        ProfileEntity profileEntity = new ProfileEntity();
        BeanUtils.copyProperties(profile,profileEntity);
        return profileEntity;
    }

    @Override
    public UserDTO mapOut(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userEntity,userDTO);
        userDTO.setProfile(mapOutProfile(userEntity.getProfile()));
        return userDTO;
    }

    private ProfileDTO mapOutProfile(ProfileEntity profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        BeanUtils.copyProperties(profile,profileDTO);
        return profileDTO;
    }

    @Override
    public List<UserEntity> mapIn(List<UserDTO> userDTO) {
        if(CollectionUtils.isEmpty(userDTO))
            return new ArrayList<>();
        return userDTO.stream()
                .map(this::mapIn)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> mapOut(List<UserEntity> userEntity) {
        if(CollectionUtils.isEmpty(userEntity))
            return new ArrayList<>();
        return userEntity.stream()
                .map(this::mapOut)
                .collect(Collectors.toList());
    }
}
