package com.example.demo.security;

import com.example.demo.DTOs.TeamMember;
import com.example.demo.data.User;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private final UserContext userContext;

    @Autowired
    public UserService (UserRepository userRepository, UserContext userContext){
        this.userRepository = userRepository;
        this.userContext = userContext;
    }

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
                    registrationForm.getEmail()
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
                    email
            );
            userRepository.save(user);
        }
    }

    public User getUser() throws ObjectNotFoundException {
        User user = userRepository.findByEmail(userContext.getEmail());
        if (user != null) return user;
        else throw new ObjectNotFoundException("Something went wrong while trying to fetch currently logged in user");
    }

    public User getUser(Long id) throws ObjectNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new ObjectNotFoundException("Something went wrong while trying to fetch currently logged in user");
        else return user.get();
    }

    public TeamMember getTeamMember() throws ObjectNotFoundException {
        User user = getUser();
        return new TeamMember(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail());
    }


}
