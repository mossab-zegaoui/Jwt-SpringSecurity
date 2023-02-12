package com.example.jwtspringsecuritydemo.api.repositiries;

import com.example.jwtspringsecuritydemo.api.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String roleName);
}
