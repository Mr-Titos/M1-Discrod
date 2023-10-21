package fr.discrod.discrod.repositories;

import fr.discrod.discrod.modeles.FileModel;
import fr.discrod.discrod.modeles.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<FileModel, String> {
    List<FileModel> findByOwner(UserModel userModel);
}