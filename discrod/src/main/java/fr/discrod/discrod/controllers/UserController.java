package fr.discrod.discrod.controllers;

import fr.discrod.discrod.exceptions.ItemNotCompleteException;
import fr.discrod.discrod.exceptions.ItemNotFoundException;
import fr.discrod.discrod.modeles.UserModel;
import fr.discrod.discrod.repositories.UserRepository;
import fr.discrod.discrod.requestModeles.AuthRequest;
import fr.discrod.discrod.requestModeles.SubscriptionRequest;
import fr.discrod.discrod.requestModeles.UserRequest;
import fr.discrod.discrod.responseModeles.UserResponse;
import fr.discrod.discrod.security.JwtUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/subscribe")
    public String subscribe(@RequestBody SubscriptionRequest request) {
        if (!request.getEmail().isEmpty()
                && !request.getPassword().isEmpty()
                && !request.getUsername().isEmpty()) {
            UserModel userModel = new UserModel();

            BeanUtils.copyProperties(request, userModel);

            userModel.setPassword(this.passwordEncoder.encode(request.getPassword()));

            this.repository.save(userModel);

            return userModel.getId();
        } else {
            throw new ItemNotCompleteException();
        }

    }

    @PostMapping("/auth")
    public String auth(@RequestBody AuthRequest request) {
        // We're letting SPRING SECURITY doing the work
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

            authentication = this.authenticationManager.authenticate(authentication);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return JwtUtils.generate(request.getUsername());
        }

        catch (BadCredentialsException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/{id}")
    public UserResponse get(@PathVariable String id) {
        UserModel usr = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        return new UserResponse(usr);
    }
    @GetMapping
    public List<UserResponse> getAll() {
        List<UserResponse> response = new ArrayList<>();
        repository.findAll().forEach(u -> response.add(new UserResponse(u)));
        return response;
    }

    @PutMapping()
    public UserResponse update(@RequestBody UserRequest itemRequest) {
        var item = repository.findById(itemRequest.getId()).orElseThrow(ItemNotFoundException::new);
        if (repository.findById(itemRequest.getId()).isPresent()) {
            UserModel newItem = new UserModel();
            BeanUtils.copyProperties(itemRequest, newItem);
            item = repository.save(newItem);
        }

        return new UserResponse(item);
    }

    @PostMapping
    public UserResponse create(@RequestBody UserRequest userRequest) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userRequest, userModel);
        repository.save(userModel);
        return new UserResponse(userModel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        UserModel userModel = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        repository.delete(userModel);
    }
}