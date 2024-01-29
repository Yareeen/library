package com.book.library;

import com.book.library.model.User;
import com.book.library.repository.UserRepository;
import com.book.library.security.PasswordConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.book.library.model.Role.ROLE_USER;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {
	private final PasswordConfig passwordEncoder;
	private final UserRepository userRepository;


	public LibraryApplication(PasswordConfig passwordEncoder, UserRepository userRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createnewUser();
	}


	private void createnewUser(){
		User user1 = new User(
				"Yaren",
				"pas",
				passwordEncoder.passwordEncoder().encode("yarencan"),
				ROLE_USER, // You need to replace Role.USER with the actual role
				true,
				true,
				true,
				true
		);

		// Perform any logic related to user creation or save it to the database
		userRepository.save(user1);
	}
}
