package fr.discrod.discrod.responseModeles;

import fr.discrod.discrod.enums.ChannelType;
import fr.discrod.discrod.modeles.ChannelModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ChannelResponse {
    private String id;
    private String guild;
    private ChannelType channelType;
    private List<MessageResponse> messageModel;

    public ChannelResponse(ChannelModel channelModel) {
        this.setId(channelModel.getId());
        this.setGuild(channelModel.getGuild());
        this.setChannelType(channelModel.getChannelType());
        List<MessageResponse> listMsg = new ArrayList<>();
        channelModel.getMessageModel().forEach(messageModel -> listMsg.add(new MessageResponse((messageModel))));
        this.setMessageModel(listMsg);
    }
}