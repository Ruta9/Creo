package com.example.demo.repositories;

import com.example.demo.data.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    User findByEmail(String username);
}
