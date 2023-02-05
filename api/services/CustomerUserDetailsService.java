package com.example.springsecurity.api.services;

import com.example.springsecurity.api.mappers.MapUserEntityToUserDetails;
import com.example.springsecurity.api.entities.UserEntity;
import com.example.springsecurity.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomerUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByName(username);
        return new MapUserEntityToUserDetails(user);
    }
}
