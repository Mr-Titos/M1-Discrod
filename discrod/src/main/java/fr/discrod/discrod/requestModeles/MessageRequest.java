package fr.discrod.discrod.requestModeles;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MessageRequest {
    private String id;
    @NotBlank
    private String text;
    @NotBlank
    private String sender_id;
}