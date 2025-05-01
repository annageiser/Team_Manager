package com.teammanager.backend.service;

import com.teammanager.backend.model.Player;
import com.teammanager.backend.repository.PlayerRepository;
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
}
