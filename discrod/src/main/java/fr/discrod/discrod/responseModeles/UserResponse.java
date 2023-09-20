package fr.discrod.discrod.responseModeles;

import fr.discrod.discrod.modeles.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private long id;
    private String username;
    private String email;
    private List<UserResponse> friends = new ArrayList<>();

    public UserResponse(User user) {
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setEmail(user.getEmail());
        this.setFriends(BuildFriendResponse(user.getFriends()));
    }
    private static List<UserResponse> BuildFriendResponse(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach(u -> {
            UserResponse ur = new UserResponse();
            ur.setId(u.getId());
            ur.setEmail(u.getEmail());
            ur.setUsername(u.getUsername());
            ur.setFriends(null);
            userResponses.add(ur);
        });
        return userResponses;
    }
}
