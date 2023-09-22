package fr.discrod.discrod.responseModeles;

import fr.discrod.discrod.enums.Role;
import fr.discrod.discrod.modeles.UserModel;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private String id;
    private String username;
    private String email;
    private Role role;
    private List<UserMinResponse> friends = new ArrayList<>();

    public UserResponse(UserModel userModel) {
        this.setId(userModel.getId());
        this.setUsername(userModel.getUsername());
        this.setEmail(userModel.getEmail());
        this.setRole(userModel.getRole());
        this.setFriends(BuildFriendResponse(userModel.getFriends()));
    }
    private static List<UserMinResponse> BuildFriendResponse(List<UserModel> userModels) {
        List<UserMinResponse> userResponses = new ArrayList<>();
        userModels.forEach(u -> {
            UserMinResponse ur = new UserMinResponse();
            ur.setId(u.getId());
            ur.setEmail(u.getEmail());
            ur.setUsername(u.getUsername());
            userResponses.add(ur);
        });
        return userResponses;
    }
}
