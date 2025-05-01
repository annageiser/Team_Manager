package ch.fhnw.teammanager.repository;

import ch.fhnw.teammanager.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {}
