package com.teammanager.backend.controller;

import com.teammanager.backend.model.Player;
import com.teammanager.backend.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Player> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Player create(@RequestBody Player player) {
        return service.save(player);
    }
}
