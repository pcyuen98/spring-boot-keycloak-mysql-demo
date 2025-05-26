package com.demo.httpsession.model.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.httpsession.model.bean.UserHistoryDTO;
import com.demo.httpsession.model.entity.UserHistoryEntity;
import com.demo.httpsession.model.entity.mapper.UserHistorySMapper;
import com.demo.httpsession.model.repository.IUserHistoryRepository;
import com.demo.httpsession.model.repository.IUserRepository;

@Service
public class UserHistoryService implements IService<UserHistoryDTO, UserHistoryEntity> {

	private final IUserHistoryRepository repository;
	private final IUserRepository iUserRepository;
	private final UserHistorySMapper userHistorySMapper;

	public UserHistoryService(IUserHistoryRepository repository, UserHistorySMapper userHistorySMapper
			,IUserRepository iUserRepository 
			) {
		this.repository = repository;
		this.userHistorySMapper = userHistorySMapper;
		this.iUserRepository = iUserRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserHistoryEntity> toList() {

		return repository.findAll();
	}

	@Override
	@Transactional
	public UserHistoryDTO save(UserHistoryDTO userHistoryDTO) {
		UserHistoryEntity userHistoryEntity = userHistorySMapper.toEntity(userHistoryDTO);
		userHistoryEntity.setLoginDate(new Date());
		userHistoryEntity.getUsersEntity().setLastUpdatedDate(userHistoryEntity.getLoginDate());
		userHistoryEntity = repository.save(userHistoryEntity);
		iUserRepository.save(userHistoryEntity.getUsersEntity());
		return userHistorySMapper.toDto(userHistoryEntity);
	}


	@Override
	@Transactional
	public void delete(UserHistoryEntity entity) {
		repository.delete(entity);
	}

}
