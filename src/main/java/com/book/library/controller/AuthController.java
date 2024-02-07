package com.book.library.controller;

import com.book.library.DTO.AuthResponse;
import com.book.library.DTO.CreateUserRequestDto;
import com.book.library.DTO.LoginRequest;
import com.book.library.service.AuthService;
import com.book.library.service.UserService;
import com.book.library.utils.ResponseCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;
    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseCreator<Long>> registerUser(@RequestBody CreateUserRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseCreator<>(userService.createUser(requestDto).getId()));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseCreator<AuthResponse>> login(@RequestBody LoginRequest loginDto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseCreator<>(authService.login(loginDto)));
    }

}
