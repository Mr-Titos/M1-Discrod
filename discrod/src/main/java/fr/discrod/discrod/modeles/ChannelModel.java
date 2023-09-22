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

    //@ManyToOne
    //@JoinColumn(name = "sender_id")
    private String guild; // TODO : Replace with SERVERMODEL

    private ChannelType channelType;

    @OneToMany
    private List<MessageModel> messageModel;
}