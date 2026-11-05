package com.demo.hotelreservation.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.hotelreservation.entity.UserAccount;
import com.demo.hotelreservation.repository.UserAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserAccount account = userAccountRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found: " + username));

        String role = account.getRole();

        if (role.startsWith("ROLE_")) {
            role = role.substring(5);
        }

        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .roles(role)
                .build();
    }
}
