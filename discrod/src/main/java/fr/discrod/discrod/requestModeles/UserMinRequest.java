package fr.discrod.discrod.requestModeles;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

// Template for user add
@Getter @Setter
public class UserMinRequest {
    @NotBlank
    private String id;
}
