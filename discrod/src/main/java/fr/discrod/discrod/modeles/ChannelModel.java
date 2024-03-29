package fr.discrod.discrod.modeles;

import fr.discrod.discrod.enums.ChannelType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelModel {
    @Id
    @UuidGenerator
    private String id;

    private String name;

    private ChannelType channelType;

    @OneToMany
    private List<MessageModel> messageModels;

    @ManyToOne
    private GuildModel guild;
}