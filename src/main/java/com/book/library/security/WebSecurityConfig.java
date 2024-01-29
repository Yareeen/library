package com.book.library.security;

import com.book.library.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


//Oluşturulan ilk sınıf
@Configuration //Konfigürasyon sınıfı olduğunu belirtmek için
@EnableWebSecurity //Security Filter Chaini enabled eder.
@EnableMethodSecurity
public class WebSecurityConfig  {
    private final JwtAuthFilter jwtAuthFilter;
    private final UserService userService;
    private final PasswordConfig passwordConfig;

    public WebSecurityConfig(JwtAuthFilter jwtAuthFilter, UserService userService, PasswordConfig passwordConfig) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userService = userService;
        this.passwordConfig = passwordConfig;
    }

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
                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Özellikle token tabanlı kimlik doğrulama (token-based authentication) kullanıldığında, SessionCreationPolicy.STATELESS ayarı sıklıkla tercih edilir. Token tabanlı kimlik doğrulama, her isteğin kendi kimlik bilgilerini taşıdığı ve sunucunun durumunu (state) saklamadığı bir mekanizmadır.
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); //gelen HTTP isteklerini kontrol eder, gerekirse kimlik doğrulamasını yapar ve bir kullanıcı için bir JWT (JSON Web Token) oluşturur veya doğrular. validate işlemi yapar.

         return security.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordConfig.passwordEncoder());
        return authenticationProvider;
    }

    @Bean //gelen isteği tokena çevirmeden önce authenticate işlemi yapar.
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();

    }

}
