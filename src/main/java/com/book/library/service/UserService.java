package com.book.library.service;

import com.book.library.model.User;
import com.book.library.repository.UserRepository;
import com.book.library.security.WebSecurityConfig;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;
    private final WebSecurityConfig webSecurityConfig;


    public UserService(UserRepository userRepository, WebSecurityConfig webSecurityConfig) {
        this.userRepository = userRepository;
        this.webSecurityConfig = webSecurityConfig;
    }


    //TODO: user entityi dtoya çevir.
    public Optional<User> getByUserName(String username){
        return userRepository.findByUsername(username);
    }

    public User createUser(User user) {
        // Assuming you have a constructor or a builder method in your User class
        User newUser = new User();

        // Set properties for the new user based on the provided user
        newUser.setName(user.getName());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(webSecurityConfig.passwordEncoder().encode(user.getPassword()));
        newUser.setAuthorities(user.getAuthorities());
        newUser.setAccountNonExpired(true);
        newUser.setEnabled(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);

        // Return the newly created user
        return userRepository.save(newUser);
    }
}
