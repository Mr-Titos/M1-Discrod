package fr.discrod.discrod.responseModeles;

import fr.discrod.discrod.enums.FileType;
import fr.discrod.discrod.modeles.FileModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileResponse {
    private String id;
    private FileType type;
    private UserMinResponse owner;
    private byte[] data;

    public FileResponse(FileModel fileModel) {
        this.setId(fileModel.getId());
        this.setType(fileModel.getType());
        this.setOwner(new UserMinResponse(fileModel.getOwner()));
        this.setData(fileModel.getData());
    }
}
