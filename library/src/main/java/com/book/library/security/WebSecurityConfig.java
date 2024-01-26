package com.book.library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;


//Oluşturulan ilk sınıf
@Configuration //Konfigürasyon sınıfı olduğunu belirtmek için
@EnableWebSecurity //Security Filter Chaini enabled eder.
public class WebSecurityConfig  {
    @Bean
    //public PasswordEncoder Sha-1 kullanır
    public BCryptPasswordEncoder passwordEncoder(){ //Base64 kullanır.
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{

        security
                .headers(x -> x.frameOptions().disable())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable) //login formu çıkmaz. headerla yollarız.
                .authorizeRequests()
                .antMatchers("/auth/**", "/library/**","/h2-console/**").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/librarian/**").hasRole("LIBRARIAN")
                .anyRequest().authenticated()
                .and()
                .httpBasic(Customizer.withDefaults());

         return security.build();
    }

}
