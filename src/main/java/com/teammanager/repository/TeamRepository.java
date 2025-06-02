package com.teammanager.repository;

import com.teammanager.model.Team;
import com.teammanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByCoach(User coach);
    List<Team> findByPlayersContaining(User player);
    List<Team> findBySport(String sport);
} 