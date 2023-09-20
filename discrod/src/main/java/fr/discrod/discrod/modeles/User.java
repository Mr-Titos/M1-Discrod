package fr.discrod.discrod.modeles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "[user]")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    @ManyToMany
    private List<User> friends = new ArrayList<>();

    public void addFriend(User u) {
        if (this.friends == null) {
            this.friends = new ArrayList<>();
        }
        this.friends.add(u);
    }

    public void removeFriend(User u) {
        if (this.friends == null) {
            this.friends = new ArrayList<>();
        }
        this.friends.remove(u);
    }
}