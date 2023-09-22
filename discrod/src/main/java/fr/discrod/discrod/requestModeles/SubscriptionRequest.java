package fr.discrod.discrod.requestModeles;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SubscriptionRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Email
    private String email;
}
