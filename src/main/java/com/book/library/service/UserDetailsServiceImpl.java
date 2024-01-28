package com.book.library.service;

import com.book.library.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {
   private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.getByUserName(username);
        return user.orElseThrow(EntityNotFoundException::new);
    }
}
