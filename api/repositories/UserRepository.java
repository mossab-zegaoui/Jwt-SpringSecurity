package com.example.springsecurity.api.repositories;

import com.example.springsecurity.api.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByName(String userName);
}
