package com.demo.httpsession.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.httpsession.model.entity.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByUsername(String username);
}
