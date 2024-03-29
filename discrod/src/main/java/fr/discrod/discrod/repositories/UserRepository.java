package fr.discrod.discrod.repositories;

import fr.discrod.discrod.modeles.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, String> {
    Optional<UserModel> findByUsername(String username);
}