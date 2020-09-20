package com.example.demo;

import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.ProjectRoleRepository;
import com.example.demo.repositories.StatusRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreoApplication {

	@Autowired
	UserRepository userRepository;

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	ProjectRoleRepository projectRoleRepository;

	public static void main(String[] args) {
		SpringApplication.run(CreoApplication.class, args);
	}


}
