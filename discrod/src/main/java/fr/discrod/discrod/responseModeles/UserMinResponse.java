package fr.discrod.discrod.responseModeles;

import fr.discrod.discrod.modeles.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserMinResponse {
    private String id;
    private String username;

    public UserMinResponse(UserModel userModel) {
        this.setId(userModel.getId());
        this.setUsername(userModel.getUsername());
    }
}
