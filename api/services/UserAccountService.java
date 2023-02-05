package com.example.springsecurity.api.services;

import com.example.springsecurity.api.entities.Role;
import com.example.springsecurity.api.entities.UserEntity;

import java.util.List;

public interface UserAccountService {
    UserEntity saveUser(UserEntity user);

    Role saveRole(Role role);

    void addRoleToUser(String roleName, String userName);

    UserEntity loadUserByName(String userName);

    List<UserEntity> getAllUsers();
}
