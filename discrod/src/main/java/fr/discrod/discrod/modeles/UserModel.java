package fr.discrod.discrod.modeles;

import fr.discrod.discrod.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "[user]")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    @Id
    @UuidGenerator
    private String id;

    @Column(unique = true)
    private String username;

    private String password;

    private Role role = Role.USER;

    @Column(unique = true)
    private String email;

    @ManyToMany
    private List<UserModel> friends = new ArrayList<>();

    public void addFriend(UserModel u) {
        if (this.friends == null) {
            this.friends = new ArrayList<>();
        }
        this.friends.add(u);
    }

    public void removeFriend(UserModel u) {
        if (this.friends == null) {
            this.friends = new ArrayList<>();
        }
        this.friends.remove(u);
    }
}