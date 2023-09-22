package fr.discrod.discrod.responseModeles;

import fr.discrod.discrod.modeles.MessageModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageResponse {
    private String id;
    private String text;
    private UserMinResponse sender;

    public MessageResponse(MessageModel messageModel) {
        this.setId(messageModel.getId());
        this.setText(messageModel.getText());
        this.setSender(new UserMinResponse(messageModel.getSender()));
    }
}
