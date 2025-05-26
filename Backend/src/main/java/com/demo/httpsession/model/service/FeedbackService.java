package com.demo.httpsession.model.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.httpsession.model.bean.FeedbackDTO;
import com.demo.httpsession.model.entity.FeedbackEntity;
import com.demo.httpsession.model.entity.mapper.FeedbackSMapper;
import com.demo.httpsession.model.repository.IFeedbackRepository;

@Service
public class FeedbackService implements IService<FeedbackDTO, FeedbackEntity> {

	private final IFeedbackRepository repository;
	
	private final FeedbackSMapper feedbackSMapper;

	public FeedbackService(IFeedbackRepository repository, FeedbackSMapper feedbackSMapper) {
		this.repository = repository;
		this.feedbackSMapper = feedbackSMapper;
	}

	@Override
	@Transactional(readOnly = true)
	public List<FeedbackEntity> toList() {

		return repository.findAll();
	}

	@Override
	@Transactional
	public FeedbackDTO save(FeedbackDTO feedbackDTO) {
		return feedbackSMapper.toDto(repository.save(feedbackSMapper.toEntity(feedbackDTO)));
	}

	@Override
	@Transactional
	public void delete(FeedbackEntity entity) {
		repository.delete(entity);
	}
}
