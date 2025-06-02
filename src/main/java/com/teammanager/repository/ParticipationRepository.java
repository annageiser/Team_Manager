package com.teammanager.repository;

import com.teammanager.model.Participation;
import com.teammanager.model.Event;
import com.teammanager.model.User;
import com.teammanager.model.ParticipationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    List<Participation> findByEvent(Event event);
    List<Participation> findByPlayer(User player);
    List<Participation> findByEventAndStatus(Event event, ParticipationStatus status);
    Optional<Participation> findByEventAndPlayer(Event event, User player);
    int countByEventAndStatus(Event event, ParticipationStatus status);
} 