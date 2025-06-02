package com.teammanager.controller;

import com.teammanager.model.Team;
import com.teammanager.model.User;
import com.teammanager.service.TeamService;
import com.teammanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    
    @Autowired
    private TeamService teamService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getTeamById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(teamService.getTeamById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'COACH')")
    public ResponseEntity<?> createTeam(@RequestBody Team team, Authentication auth) {
        User coach = userService.getUserByUsername(auth.getName())
            .orElseThrow(() -> new RuntimeException("User not found"));
        team.setCoach(coach);
        return ResponseEntity.ok(teamService.createTeam(team));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @teamService.getTeamById(#id).coach.username == authentication.name")
    public ResponseEntity<?> updateTeam(@PathVariable Long id, @RequestBody Team teamDetails) {
        try {
            Team updatedTeam = teamService.updateTeam(id, teamDetails);
            return ResponseEntity.ok(updatedTeam);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping("/{teamId}/players/{playerId}")
    @PreAuthorize("hasRole('ADMIN') or @teamService.getTeamById(#teamId).coach.username == authentication.name")
    public ResponseEntity<?> addPlayerToTeam(@PathVariable Long teamId, @PathVariable Long playerId) {
        try {
            Team updatedTeam = teamService.addPlayerToTeam(teamId, playerId);
            return ResponseEntity.ok(updatedTeam);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{teamId}/players/{playerId}")
    @PreAuthorize("hasRole('ADMIN') or @teamService.getTeamById(#teamId).coach.username == authentication.name")
    public ResponseEntity<?> removePlayerFromTeam(@PathVariable Long teamId, @PathVariable Long playerId) {
        try {
            Team updatedTeam = teamService.removePlayerFromTeam(teamId, playerId);
            return ResponseEntity.ok(updatedTeam);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        try {
            teamService.deleteTeam(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
} 