package com.book.library.service;

import com.book.library.DTO.CreateUserRequestDto;
import com.book.library.model.User;
import com.book.library.repository.UserRepository;
import com.book.library.security.PasswordConfig;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordConfig passwordEncoder;

    public UserService(UserRepository userRepository, PasswordConfig passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Optional<User> getByUserName(String username){
        return userRepository.findByUsername(username);
    }


    public User createUser(CreateUserRequestDto requestDto) {
        // Assuming you have a constructor or a builder method in your User class
        User newUser = new User();
        // Set properties for the new requestDto based on the provided requestDto
        newUser.setName(requestDto.getName());
        newUser.setUsername(requestDto.getUsername());
        newUser.setPassword(passwordEncoder.passwordEncoder().encode(requestDto.getPassword()));
        newUser.setAuthorities(requestDto.getAuthorities());

        // Return the newly created requestDto
        return userRepository.save(newUser);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(EntityNotFoundException::new);
    }
}
