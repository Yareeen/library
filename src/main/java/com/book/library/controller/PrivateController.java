package com.book.library.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/admin")
public class PrivateController {
    @GetMapping
    public String hello(){
        return "hello private";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String helloUser(){
        return "hello private user";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String helloAdmin(){
        return "hello private admin";
    }
}
