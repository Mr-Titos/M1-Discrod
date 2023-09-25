package fr.discrod.discrod.requestModeles;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GuildRequest {
    private String id;

    @NotBlank
    private String name;
}