package com.teammanager.repository;

import com.teammanager.model.Event;
import com.teammanager.model.Team;
import com.teammanager.model.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByTeam(Team team);
    List<Event> findByTeamAndStartTimeBetween(Team team, LocalDateTime start, LocalDateTime end);
    List<Event> findByType(EventType type);
    List<Event> findByStartTimeGreaterThanEqual(LocalDateTime startTime);
} 