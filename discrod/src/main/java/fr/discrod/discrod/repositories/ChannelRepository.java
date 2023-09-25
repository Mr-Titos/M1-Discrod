package fr.discrod.discrod.repositories;

import fr.discrod.discrod.modeles.ChannelModel;
import fr.discrod.discrod.modeles.GuildModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChannelRepository extends JpaRepository<ChannelModel, String> {
    List<ChannelModel> findAllByGuild(GuildModel guildModel);
}