package com.book.library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
public class PasswordConfig {

    //public PasswordEncoder Sha-1 kullanır
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){ //Base64 kullanır.
        return new BCryptPasswordEncoder();
    }
}
