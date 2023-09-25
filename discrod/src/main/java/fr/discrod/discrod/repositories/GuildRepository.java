package fr.discrod.discrod.repositories;

import fr.discrod.discrod.modeles.GuildModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuildRepository extends JpaRepository<GuildModel, String> {
}