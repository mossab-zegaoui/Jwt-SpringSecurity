package com.example.springsecurity;

import com.example.springsecurity.api.entities.Role;
import com.example.springsecurity.api.entities.UserEntity;
import com.example.springsecurity.api.services.UserAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserAccountService accountService) {
        return args -> {
            accountService.saveRole(new Role(null, "ADMIN"));
            accountService.saveRole(new Role(null, "EMPLOYEE"));
            accountService.saveRole(new Role(null, "STUDENT"));
//
            accountService.saveUser(new UserEntity(null, "Mossaab", "user123", null));
            accountService.saveUser(new UserEntity(null, "Anass", "user123", null));
            accountService.saveUser(new UserEntity(null, "Omar", "user123", null));
//
            accountService.addRoleToUser("ADMIN", "Mossaab");
            accountService.addRoleToUser("STUDENT", "Anass");
            accountService.addRoleToUser("EMPLOYEE", "Omar");


        };
    }

}
