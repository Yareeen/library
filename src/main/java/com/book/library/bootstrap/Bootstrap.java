package com.book.library.bootstrap;

import com.book.library.model.User;
import com.book.library.repository.UserRepository;
import com.book.library.security.PasswordConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.util.Collections;

import static com.book.library.model.Role.ROLE_ADMIN;
@Component
public class Bootstrap implements CommandLineRunner {

    private final PasswordConfig passwordConfig;
    private final UserRepository userRepository;

    public Bootstrap(PasswordConfig passwordConfig, UserRepository userRepository) {
        this.passwordConfig = passwordConfig;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        bootstrapAdmin();
    }
    private void bootstrapAdmin(){

        User admin = new User(
                "Yaren",
                "adminyaren",
                passwordConfig.passwordEncoder().encode("pass"),
                Collections.singleton(ROLE_ADMIN),
                true,
                true,
                true,
                true
        );

        userRepository.save(admin);
    }

}
