package fr.discrod.discrod.requestModeles;

import fr.discrod.discrod.modeles.UserModel;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MessageRequest {
    private String id;
    private String text;
    private UserModel sender;
}