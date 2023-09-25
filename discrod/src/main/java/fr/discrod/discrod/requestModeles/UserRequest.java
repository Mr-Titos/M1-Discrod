package fr.discrod.discrod.requestModeles;

import fr.discrod.discrod.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequest {
    private String id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Email
    private String email;

    private Role role;
}