package com.teammanager.service;

import com.teammanager.model.Team;
import com.teammanager.model.User;
import com.teammanager.repository.TeamRepository;
import com.teammanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeamService {
    
    @Autowired
    private TeamRepository teamRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }
    
    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Team not found"));
    }
    
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
    
    public List<Team> getTeamsByCoach(User coach) {
        return teamRepository.findByCoach(coach);
    }
    
    public List<Team> getTeamsByPlayer(User player) {
        return teamRepository.findByPlayersContaining(player);
    }
    
    public Team updateTeam(Long id, Team teamDetails) {
        Team team = getTeamById(id);
        
        team.setName(teamDetails.getName());
        team.setSport(teamDetails.getSport());
        team.setDescription(teamDetails.getDescription());
        
        return teamRepository.save(team);
    }
    
    public Team addPlayerToTeam(Long teamId, Long playerId) {
        Team team = getTeamById(teamId);
        User player = userRepository.findById(playerId)
            .orElseThrow(() -> new RuntimeException("Player not found"));
            
        if (!team.getPlayers().contains(player)) {
            team.getPlayers().add(player);
            return teamRepository.save(team);
        }
        return team;
    }
    
    public Team removePlayerFromTeam(Long teamId, Long playerId) {
        Team team = getTeamById(teamId);
        User player = userRepository.findById(playerId)
            .orElseThrow(() -> new RuntimeException("Player not found"));
            
        team.getPlayers().remove(player);
        return teamRepository.save(team);
    }
    
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
} 