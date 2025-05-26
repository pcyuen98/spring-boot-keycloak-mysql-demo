package com.demo.httpsession.model.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.demo.httpsession.model.bean.FeedbackDTO;
import com.demo.httpsession.model.entity.FeedbackEntity;

@Mapper
public interface FeedbackSMapper {

	@Mapping(target = "userDTO", source = "usersEntity")
	FeedbackDTO toDto(FeedbackEntity entity);

	@Mapping(target = "usersEntity", source = "userDTO")
	FeedbackEntity toEntity(FeedbackDTO dto);
}
