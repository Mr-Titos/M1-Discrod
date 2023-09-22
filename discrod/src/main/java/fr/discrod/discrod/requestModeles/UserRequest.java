package fr.discrod.discrod.requestModeles;

import fr.discrod.discrod.enums.Role;
import fr.discrod.discrod.modeles.UserModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UserRequest {
    private String id;
    private String username;
    private String password;
    private String email;
    private Role role = Role.USER;
    private List<UserModel> friends;
}