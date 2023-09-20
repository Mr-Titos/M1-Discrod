package fr.discrod.discrod.repositories;

import fr.discrod.discrod.modeles.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}