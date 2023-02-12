package com.example.jwtspringsecuritydemo.api.services;

import com.example.jwtspringsecuritydemo.api.mappers.MapUserToUserDetails;
import com.example.jwtspringsecuritydemo.api.repositiries.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new MapUserToUserDetails(userRepository.findByUserName(username));
    }
}
