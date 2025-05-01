package ch.fhnw.teammanager.repository;

import ch.fhnw.teammanager.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {}
