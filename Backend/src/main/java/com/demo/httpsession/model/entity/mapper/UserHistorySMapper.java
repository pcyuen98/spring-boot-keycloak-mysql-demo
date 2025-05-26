package com.demo.httpsession.model.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.demo.httpsession.model.bean.UserHistoryDTO;
import com.demo.httpsession.model.entity.UserHistoryEntity;

@Mapper
public interface UserHistorySMapper {

	@Mapping(target = "userDTO", source = "usersEntity")
	UserHistoryDTO toDto(UserHistoryEntity entity);

	@Mapping(target = "usersEntity", source = "userDTO")
	UserHistoryEntity toEntity(UserHistoryDTO dto);

}