package com.example.demo.security;

import com.example.demo.data.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void register (RegistrationForm registrationForm) throws UserAlreadyExistsException {
        User user = userRepository.findByEmail(registrationForm.getEmail());
        if (user != null) {
            throw new UserAlreadyExistsException("User with provided email already exists");
        }
        else {
            user = new User(
                    registrationForm.getFirstname(),
                    registrationForm.getLastname(),
                    encoder.encode(registrationForm.getPassword()),
                    registrationForm.getEmail(),
                    RegistrationType.FORM
                    );
            userRepository.save(user);
        }
    }

    public void register (String firstname, String lastname, String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User (
                    firstname,
                    lastname,
                    null,
                    email,
                    RegistrationType.OAUTH
            );
            userRepository.save(user);
        }
    }

    //Method to get User roles

}
