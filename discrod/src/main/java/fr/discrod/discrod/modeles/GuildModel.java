package fr.discrod.discrod.modeles;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="guild")
public class GuildModel {
    @Id
    @UuidGenerator
    private String id;

    private String name;

    @ManyToMany(mappedBy = "guilds")
    private List<UserModel> users;

    @OneToMany(mappedBy = "guild")
    private List<ChannelModel> channels;
}