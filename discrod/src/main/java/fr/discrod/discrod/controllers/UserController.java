package fr.discrod.discrod.controllers;

import fr.discrod.discrod.enums.Role;
import fr.discrod.discrod.exceptions.FriendAlreadyInListException;
import fr.discrod.discrod.exceptions.ItemNotFoundException;
import fr.discrod.discrod.exceptions.ItemNotValidException;
import fr.discrod.discrod.modeles.UserModel;
import fr.discrod.discrod.repositories.UserRepository;
import fr.discrod.discrod.requestModeles.AuthRequest;
import fr.discrod.discrod.requestModeles.SubscriptionRequest;
import fr.discrod.discrod.requestModeles.UserMinRequest;
import fr.discrod.discrod.requestModeles.UserRequest;
import fr.discrod.discrod.responseModeles.UserResponse;
import fr.discrod.discrod.security.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/subscribe")
    public String subscribe(@Validated @RequestBody SubscriptionRequest request, BindingResult errors) {
        ResolveErrors(errors);

        if (!request.getEmail().isEmpty()
                && !request.getPassword().isEmpty()
                && !request.getUsername().isEmpty()) {
            UserModel userModel = new UserModel();

            BeanUtils.copyProperties(request, userModel);

            userModel.setPassword(this.passwordEncoder.encode(request.getPassword()));

            this.repository.save(userModel);

            return userModel.getId();
        } else {
            throw new ItemNotValidException();
        }

    }

    @PostMapping("/auth")
    public String auth(@Validated @RequestBody AuthRequest request, BindingResult errors) {
        ResolveErrors(errors);
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
    public UserResponse update(@Valid @RequestBody UserRequest itemRequest, BindingResult errors) {
        ResolveErrors(errors);

        var item = repository.findById(itemRequest.getId()).orElseThrow(ItemNotFoundException::new);
        if (repository.findById(itemRequest.getId()).isPresent()) {
            UserModel newItem = new UserModel();
            BeanUtils.copyProperties(itemRequest, newItem);
            item = repository.save(newItem);
        }

        return new UserResponse(item);
    }

    @PostMapping
    public UserResponse create(@Valid @RequestBody UserRequest userRequest, BindingResult errors) {
        ResolveErrors(errors);

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userRequest, userModel);
        userModel.setFriends(new ArrayList<>());
        userModel.setGuilds(new ArrayList<>());
        if(userModel.getRole() == null) {
          userModel.setRole(Role.USER);
        }
        repository.save(userModel);
        return new UserResponse(userModel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        UserModel userModel = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        repository.delete(userModel);
    }

    @PostMapping("/friends/{id}")
    public void addFriend(@Validated @RequestBody UserMinRequest itemRequest, @PathVariable String id, BindingResult errors) {
        ResolveErrors(errors);

        var item = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        var itemUser = repository.findById(itemRequest.getId()).orElseThrow(ItemNotFoundException::new);
        if (item.getFriends().contains(itemUser))
            throw new FriendAlreadyInListException();
        if (itemUser == item)
            throw new RuntimeException("Nice try");

        itemUser.getFriends().add(item);
        repository.save(item);
    }

    @DeleteMapping("/friends/{id}")
    public void removeFriend(@Validated @RequestBody UserMinRequest itemRequest, @PathVariable String id, BindingResult errors) {
        ResolveErrors(errors);

        var item = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        var itemUser = repository.findById(itemRequest.getId()).orElseThrow(ItemNotFoundException::new);
        itemUser.getFriends().remove(item);
        repository.save(item);
    }

    private void ResolveErrors(BindingResult errors) {
        if(errors.hasErrors())
            throw new ItemNotValidException();
    }
}