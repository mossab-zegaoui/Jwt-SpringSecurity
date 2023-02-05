package com.example.springsecurity.api.web;

import com.example.springsecurity.api.entities.UserDTO;
import com.example.springsecurity.api.entities.UserEntity;
import com.example.springsecurity.api.services.JwtService;
import com.example.springsecurity.api.services.UserAccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserAccountService userAccountService;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public UserController(UserAccountService userAccountService,
                          JwtService jwtService,
                          AuthenticationManager authenticationManager) {
        this.userAccountService = userAccountService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping
    public List<UserEntity> getUsers() {
        return userAccountService.getAllUsers();
    }

    @GetMapping("/{userName}")
    @PreAuthorize("hasAuthority('ROle_USER')")
    public UserEntity getUser(@PathVariable String userName) {
        return userAccountService.loadUserByName(userName);
    }

    @PostMapping
    public UserEntity saveUser(@RequestBody UserEntity user) {
        return userAccountService.saveUser(user);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping
    public UserEntity updateUser(@RequestBody UserEntity user) {
        return userAccountService.saveUser(user);
    }

    @PostMapping("/register")
    public String register(@RequestBody UserDTO user) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUserName(),
                        user.getPassword()));
        if (authenticate.isAuthenticated())
            return jwtService.generateToken(user.getUserName());
        else
            return "userName: " + user.getUserName() + " is not in our database";
    }
}
