package com.lec.board.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 실제 DB 연동 필요
        if ("admin".equals(username)) {
            return new User("admin", "{noop}password", Collections.emptyList());
        }
        throw new UsernameNotFoundException("User not found");
    }
}
