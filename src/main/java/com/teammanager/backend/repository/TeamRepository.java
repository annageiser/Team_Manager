package com.teammanager.backend.repository;

import com.teammanager.backend.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {}
