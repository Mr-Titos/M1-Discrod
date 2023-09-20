package fr.discrod.discrod.controllers;

import fr.discrod.discrod.modeles.User;
import fr.discrod.discrod.requestModeles.UserRequest;
import fr.discrod.discrod.responseModeles.UserResponse;
import fr.discrod.discrod.repositories.UserRepository;
import fr.discrod.discrod.exceptions.ItemNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        User usr = userRepository.findById(id).orElseThrow(ItemNotFoundException::new);
        return new UserResponse(usr);
    }
    @GetMapping
    public List<UserResponse> getUsers() {
        List<UserResponse> response = new ArrayList<>();
        userRepository.findAll().forEach(u -> response.add(new UserResponse(u)));
        return response;
    }

    @PutMapping()
    public User updateUser(@RequestBody User user) {
        var response = userRepository.findById(user.getId()).orElseThrow(ItemNotFoundException::new);
        if (userRepository.findById(user.getId()).isPresent())
            response = userRepository.save(user);

        return response;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);
        userRepository.save(user);
        return new UserResponse(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        User user = userRepository.findById(id).orElseThrow(ItemNotFoundException::new);
        userRepository.delete(user);
    }
}