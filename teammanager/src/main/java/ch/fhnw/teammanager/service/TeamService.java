package ch.fhnw.teammanager.service;

import ch.fhnw.teammanager.model.Team;
import ch.fhnw.teammanager.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public List<Team> getAll() {
        return repository.findAll();
    }

    public Team save(Team team) {
        return repository.save(team);
    }
}
