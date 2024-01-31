package com.book.library.controller;

import com.book.library.DTO.AuthResponse;
import com.book.library.DTO.CreateUserRequest;
import com.book.library.DTO.LoginRequest;
import com.book.library.repository.UserRepository;
import com.book.library.security.PasswordConfig;
import com.book.library.service.JwtService;
import com.book.library.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordConfig passwordConfig;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    public AuthController(AuthenticationManager authenticationManager, UserService userService, PasswordConfig passwordConfig, UserRepository userRepository, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordConfig = passwordConfig;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @GetMapping
    public String homepage(){
        return "Hoşgeldiniz";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody CreateUserRequest request) {

        userService.createUser(request);
        return new ResponseEntity<>("User Oluşturuldu.", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(loginDto.getUsername());
            AuthResponse authResponse = new AuthResponse(token);
            return new ResponseEntity<>(authResponse, HttpStatus.OK);

        }

        throw new UsernameNotFoundException("invalid username {} " + loginDto.getUsername());
    }


}
