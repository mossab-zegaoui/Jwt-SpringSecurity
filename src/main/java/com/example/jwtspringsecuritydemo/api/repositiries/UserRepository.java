package com.example.jwtspringsecuritydemo.api.repositiries;

import com.example.jwtspringsecuritydemo.api.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);
}
