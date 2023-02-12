package com.example.jwtspringsecuritydemo.api.services;

import com.example.jwtspringsecuritydemo.api.entities.AuthRequest;
import com.example.jwtspringsecuritydemo.api.entities.AuthResponse;
import com.example.jwtspringsecuritydemo.api.entities.Role;
import com.example.jwtspringsecuritydemo.api.entities.UserEntity;

import java.util.List;

public interface AuthenticationService {
    UserEntity loadUserByName(String userName);

    UserEntity saveUser(UserEntity user);

    AuthResponse register(AuthRequest request);

    AuthResponse authenticate(AuthRequest request);

    Role saveRole(Role role);

    void assignRoleToUser(String roleName, String userName);

    List<UserEntity> getAllUsers();
}
