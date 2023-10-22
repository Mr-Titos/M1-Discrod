package fr.discrod.discrod.responseModeles;

import fr.discrod.discrod.enums.FileType;
import fr.discrod.discrod.modeles.FileModel;
import jakarta.servlet.http.HttpServletRequest;
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
    private String name;
    private String url;

    public FileResponse(FileModel fileModel, HttpServletRequest request) {
        this.setId(fileModel.getId());
        this.setType(fileModel.getType());
        this.setOwner(new UserMinResponse(fileModel.getOwner()));

        String baseURL = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/api"));
        String path = request.getServletPath();
        if (path.contains(fileModel.getId()))
            path = path.substring(0, path.lastIndexOf("file/") + 4);

        this.setUrl(baseURL + path + "/" + fileModel.getId());
    }
}
