package fr.discrod.discrod.requestModeles;

import fr.discrod.discrod.enums.Role;
import fr.discrod.discrod.modeles.UserModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UserRequest {
    private String id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Email
    private String email;
    @NotBlank
    private Role role = Role.USER;
    private List<UserModel> friends;
}