package com.demo.httpsession.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.httpsession.model.entity.FeedbackEntity;

@Repository
public interface IFeedbackRepository extends JpaRepository<FeedbackEntity, Long> {

}
