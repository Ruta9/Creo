package com.example.demo;

import com.example.demo.data.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CreoApplication {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CreoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			userRepository.save(new User("Ruta", "Jankauskaite", new BCryptPasswordEncoder().encode("admin"), "rutule9@gmail.com"));
		};
	}

}
