package com.example.jwtspringsecuritydemo;

import com.example.jwtspringsecuritydemo.api.entities.Role;
import com.example.jwtspringsecuritydemo.api.entities.UserEntity;
import com.example.jwtspringsecuritydemo.api.services.AuthenticationServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JwtSpringSecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtSpringSecurityDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AuthenticationServiceImpl authenticationService) {
        return args -> {
            authenticationService.saveUser(new UserEntity(null, "mossaab", "user123", null));
            authenticationService.saveUser(new UserEntity(null, "anass", "user123", null));
            authenticationService.saveUser(new UserEntity(null, "omar", "user123", null));
//
            authenticationService.saveRole(new Role(null, "ADMIN"));
            authenticationService.saveRole(new Role(null, "USER"));
            authenticationService.saveRole(new Role(null, "EMPLOYEE"));
//
            authenticationService.assignRoleToUser("ADMIN", "mossaab");
            authenticationService.assignRoleToUser("USER", "mossaab");
            authenticationService.assignRoleToUser("USER", "anass");
            authenticationService.assignRoleToUser("EMPLOYEE", "omar");

        };
    }

}
