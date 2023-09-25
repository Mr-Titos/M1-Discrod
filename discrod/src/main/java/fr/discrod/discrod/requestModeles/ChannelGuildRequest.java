package fr.discrod.discrod.requestModeles;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

// Template for user add in guild
@Getter @Setter
public class ChannelGuildRequest {
    @NotBlank
    private String id;
}