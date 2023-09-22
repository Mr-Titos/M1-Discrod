package fr.discrod.discrod.modeles;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageModel {
    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserModel sender;

    private String text;
}