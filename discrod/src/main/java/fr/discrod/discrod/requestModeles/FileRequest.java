package fr.discrod.discrod.requestModeles;

import fr.discrod.discrod.enums.FileType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FileRequest {
    private String id;
    @NotBlank
    private FileType type;
    @NotBlank
    private String owner_id;
    @NotBlank
    private byte[] data;
}