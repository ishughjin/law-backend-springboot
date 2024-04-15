package com.incypio.law.UserService.repository;

import com.incypio.law.UserService.entity.UserEntity;
import com.incypio.law.UserService.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

}
