package com.book.library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;


//Oluşturulan ilk sınıf
@Configuration //Konfigürasyon sınıfı olduğunu belirtmek için
@EnableWebSecurity //Security Filter Chaini enabled eder.
public class WebSecurityConfig  {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{

        security
                .headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable) //login formu çıkmaz. headerla yollarız.
                .authorizeHttpRequests(x ->
                        x
                                .requestMatchers("/auth/**","/h2-console/**").permitAll()
                                .anyRequest().authenticated())

                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

         return security.build();
    }

}
