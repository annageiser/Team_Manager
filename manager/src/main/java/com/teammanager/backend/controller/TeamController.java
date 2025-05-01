package com.teammanager.backend.controller;

import com.teammanager.backend.model.Team;
import com.teammanager.backend.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }

    @GetMapping
    public List<Team> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Team create(@RequestBody Team team) {
        return service.save(team);
    }
}
