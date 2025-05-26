package com.demo.httpsession.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.httpsession.model.bean.FeedbackDTO;
import com.demo.httpsession.model.bean.UserDTO;
import com.demo.httpsession.model.bean.UserHistoryDTO;
import com.demo.httpsession.model.entity.FeedbackEntity;
import com.demo.httpsession.model.entity.UserEntity;
import com.demo.httpsession.model.entity.UserHistoryEntity;
import com.demo.httpsession.model.entity.mapper.FeedbackSMapper;
import com.demo.httpsession.model.entity.mapper.UserSMapper;

@SpringBootTest
class FeedbackMapperTest {

    @Autowired
    private FeedbackSMapper feedbackSMapper;
    
    @Test
    void toEntityTest() {
    	
    	UserDTO userDTO = new UserDTO();
    	userDTO.setId(10000l);
    	userDTO.setName("Mapper Name");
    	
    	FeedbackDTO feedbackDTO = new FeedbackDTO();
    	feedbackDTO.setUserDTO(userDTO);
    	feedbackDTO.setId(10000l);
    	FeedbackEntity feedbackEntity = feedbackSMapper.toEntity(feedbackDTO);
    	assertNotNull(feedbackEntity);
    	assertNotNull(feedbackEntity.getUsersEntity());
    	assertNotNull(feedbackEntity.getUsersEntity().getId());
    }
    
    @Test
    void toDTOTest() {
    	
    	UserEntity userEntity = new UserEntity();
    	userEntity.setId(10000l);
    	userEntity.setName("Mapper Name");
    	
    	FeedbackEntity feedbackEntity = new FeedbackEntity();
    	feedbackEntity.setUsersEntity(userEntity);
    	feedbackEntity.setId(10000l);
    	FeedbackDTO feedbackDTO = feedbackSMapper.toDto(feedbackEntity);
    	assertNotNull(feedbackDTO);
    	assertNotNull(feedbackDTO.getUserDTO());
    	assertNotNull(feedbackDTO.getUserDTO().getId());
    }
}
