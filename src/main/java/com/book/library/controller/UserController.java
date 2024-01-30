package com.book.library.controller;

import com.book.library.DTO.CreateUserRequest;
import com.book.library.model.User;
import com.book.library.repository.UserRepository;
import com.book.library.security.PasswordConfig;
import com.book.library.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.book.library.model.Role.ROLE_USER;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;
    private final PasswordConfig passwordConfig;
    private final UserRepository userRepository;

    public UserController(UserService userService, PasswordConfig passwordConfig, UserRepository userRepository) {
        this.userService = userService;
        this.passwordConfig = passwordConfig;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String hello(){

        return "Hoşgeldiniz";
    }

    @PostMapping("/register")
    public ResponseEntity<CreateUserRequest> registerUser(@RequestBody CreateUserRequest request) {

        userService.createUser(request);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }
}
