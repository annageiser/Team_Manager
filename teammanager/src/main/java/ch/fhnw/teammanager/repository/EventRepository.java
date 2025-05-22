package ch.fhnw.teammanager.repository;

import ch.fhnw.teammanager.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByTeamId(Long teamId);
}