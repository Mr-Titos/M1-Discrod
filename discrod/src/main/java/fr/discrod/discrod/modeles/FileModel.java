package fr.discrod.discrod.modeles;

import fr.discrod.discrod.enums.FileType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileModel {
    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserModel owner;

    private FileType type;

    @Lob
    private byte[] data;
}