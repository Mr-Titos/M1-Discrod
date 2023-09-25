package fr.discrod.discrod.responseModeles;

import fr.discrod.discrod.enums.ChannelType;
import fr.discrod.discrod.modeles.ChannelModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChannelMinResponse {
    private String id;
    private String name;
    private ChannelType channelType;
    public ChannelMinResponse(ChannelModel channelModel) {
        this.setId(channelModel.getId());
        this.setChannelType(channelModel.getChannelType());
        this.setName(channelModel.getName());
    }
}