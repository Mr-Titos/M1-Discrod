package fr.discrod.discrod.requestModeles;

import fr.discrod.discrod.modeles.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UserRequest {
    private String username;
    private String password;
    private String email;
    private List<User> friends;
}