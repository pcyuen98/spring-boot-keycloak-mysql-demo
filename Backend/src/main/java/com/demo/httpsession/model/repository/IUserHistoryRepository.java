package com.demo.httpsession.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.httpsession.model.entity.UserHistoryEntity;

@Repository
public interface IUserHistoryRepository extends JpaRepository<UserHistoryEntity, Long> {
}
