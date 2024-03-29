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
    private String name;
    private ChannelType channelType;
    private List<MessageResponse> messageResponses;

    public ChannelResponse(ChannelModel channelModel) {
        this.setId(channelModel.getId());
        this.setChannelType(channelModel.getChannelType());
        this.setName(channelModel.getName());
        List<MessageResponse> listMsg = new ArrayList<>();
        channelModel.getMessageModels().forEach(messageModel -> listMsg.add(new MessageResponse((messageModel))));
        this.setMessageResponses(listMsg);
    }
}