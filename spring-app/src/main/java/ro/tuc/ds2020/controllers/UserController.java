package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.LoginRequest;
import ro.tuc.ds2020.entities.User;
import ro.tuc.ds2020.entities.UserRepository;
import ro.tuc.ds2020.services.PersonService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping()
public class UserController {

    UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable UUID id) {
        return userRepository.findById(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/users")
    public boolean addUser(@RequestBody User user) {
        user.setId(UUID.randomUUID());
        boolean userExists = userRepository.findById(user.getId()).isPresent();
        if(!userExists) {
            userRepository.save(user);
            return true;
        }
        else {
            return false;
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/users")
    public boolean updateUser(@RequestBody User user) {
        boolean userExists = userRepository.findById(user.getId()).isPresent();
        if(userExists) {
            userRepository.save(user);
            return true;
        }
        else {
            return false;
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/users/{id}")
    public boolean deleteUser(@PathVariable UUID id) {
        boolean userExists = userRepository.findById(id).isPresent();
        if(userExists) {
            userRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public Optional<User> verifyIfUserExists(@RequestBody LoginRequest loginRequest) {
        Iterable<User> usersList = getAllUsers();
        for(User user : usersList) {
            if (user.getEmail().equals(loginRequest.getEmail())) {
                if (user.getPassword().equals(loginRequest.getPassword())) {
                    return Optional.of(user);
                }
            }
        }
        return Optional.empty();
    }
}
