package ch.fhnw.teammanager.service;

import ch.fhnw.teammanager.model.Player;
import ch.fhnw.teammanager.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService{
    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public List<Player> getAll() {
        return repository.findAll();
    }

    public Player save(Player player) {
        return repository.save(player);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
