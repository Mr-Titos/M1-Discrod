package fr.discrod.discrod.repositories;

import fr.discrod.discrod.modeles.MessageModel;
import fr.discrod.discrod.modeles.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<MessageModel, String> {
    List<MessageModel> findBySender(UserModel userModel);

    Optional<List<MessageModel>> findAllByTextContaining(String text);
}