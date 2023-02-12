package com.example.jwtspringsecuritydemo.api.web;

import com.example.jwtspringsecuritydemo.api.entities.AuthRequest;
import com.example.jwtspringsecuritydemo.api.entities.AuthResponse;
import com.example.jwtspringsecuritydemo.api.entities.UserEntity;
import com.example.jwtspringsecuritydemo.api.services.AuthenticationServiceImpl;
import com.example.jwtspringsecuritydemo.api.services.CustomerUserDetailsService;
import com.example.jwtspringsecuritydemo.api.services.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class AuthController {
    private final AuthenticationServiceImpl authenticationService;
    private final AuthenticationManager authenticationManager;
    private final CustomerUserDetailsService customerUserDetailsService;
    private final JwtService jwtService;

    public AuthController(AuthenticationServiceImpl authenticationService,
                          AuthenticationManager authenticationManager,
                          CustomerUserDetailsService customerUserDetailsService,
                          JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
        this.customerUserDetailsService = customerUserDetailsService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public List<UserEntity> getUsers() {
        return authenticationService.getAllUsers();
    }

    @GetMapping("/{userName}")
    public UserEntity getUser(@PathVariable String userName) {
        return authenticationService.loadUserByName(userName);
    }

    @PostMapping
    public UserEntity saveUser(@RequestBody UserEntity user) {
        return authenticationService.saveUser(user);
    }

    @PutMapping("/{userId}")
    UserEntity updateUser(@PathVariable Long userId,
                          @RequestBody UserEntity user) {
        user.setId(userId);
        return authenticationService.saveUser(user);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<AuthResponse> refreshToken(HttpServletRequest request,
                                                     HttpServletResponse response) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String refreshToken = authHeader.substring(7);
            String userName = jwtService.extractUsername(refreshToken);
            UserDetails user = customerUserDetailsService.loadUserByUsername(userName);
            String accessToken = jwtService.generateAccessToken(user);
            return ResponseEntity.ok(AuthResponse.builder()
                    .refreshToken(refreshToken)
                    .accessToken(accessToken)
                    .build());
        }
        return null;
    }


}
