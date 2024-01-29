package com.book.library;

import com.book.library.model.User;
import com.book.library.repository.UserRepository;
import com.book.library.security.PasswordConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.book.library.model.Role.ROLE_USER;

@SpringBootApplication
public class LibraryApplication{

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

}
