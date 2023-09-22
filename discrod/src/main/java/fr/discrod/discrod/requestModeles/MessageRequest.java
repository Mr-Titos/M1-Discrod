package fr.discrod.discrod.requestModeles;

import fr.discrod.discrod.modeles.UserModel;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MessageRequest {
    private String id;
    @NotBlank
    private String text;
    @NotBlank
    private UserModel sender;
}