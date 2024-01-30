package com.book.library.service;

import com.book.library.DTO.CreateUserRequest;
import com.book.library.model.Role;
import com.book.library.model.User;
import com.book.library.repository.UserRepository;
import com.book.library.security.PasswordConfig;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static com.book.library.model.Role.ROLE_USER;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordConfig passwordEncoder;

    public UserService(UserRepository userRepository, PasswordConfig passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    //TODO: user entityi dtoya çevir.
    public Optional<User> getByUserName(String username){
        return userRepository.findByUsername(username);
    }


    public User createUser(CreateUserRequest user) {
        // Assuming you have a constructor or a builder method in your User class
        User newUser = new User();

        // Set properties for the new user based on the provided user
        newUser.setName(user.getName());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.passwordEncoder().encode(user.getPassword()));
        newUser.setAuthorities(Collections.singleton(ROLE_USER));
        newUser.setEnabled(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);

        // Return the newly created user
        return userRepository.save(newUser);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(EntityNotFoundException::new);
    }
}
