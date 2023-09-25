package fr.discrod.discrod.responseModeles;

import fr.discrod.discrod.modeles.GuildModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GuildResponse {
    private String id;
    private String name;
    private List<ChannelMinResponse> channelResponses;
    private List<UserMinResponse> userResponses;

    public GuildResponse(GuildModel guildModel) {
        this.setId(guildModel.getId());
        this.setName(guildModel.getName());
        List<ChannelMinResponse> listChannels = new ArrayList<>();
        guildModel.getChannels().forEach(channelModel -> listChannels.add(new ChannelMinResponse((channelModel))));
        this.setChannelResponses(listChannels);
        List<UserMinResponse> listUsers = new ArrayList<>();
        guildModel.getUsers().forEach(userModel -> listUsers.add(new UserMinResponse((userModel))));
        this.setUserResponses(listUsers);
    }
}