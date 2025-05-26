package com.demo.httpsession.model.entity.mapper;

import org.mapstruct.Mapper;

import com.demo.httpsession.model.bean.UserDTO;
import com.demo.httpsession.model.entity.UserEntity;

@Mapper
public interface UserSMapper {

	UserDTO toDto(UserEntity entity);

	UserEntity toEntity(UserDTO dto);

}
