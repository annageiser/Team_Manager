package com.teammanager.service.mapper;

import com.teammanager.dto.UserDTO;
import com.teammanager.dto.TeamDTO;
import com.teammanager.dto.EventDTO;
import com.teammanager.model.User;
import com.teammanager.model.Team;
import com.teammanager.model.Event;
import com.teammanager.model.ParticipationStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntityMapper {
    
    public UserDTO toUserDTO(User user) {
        if (user == null) return null;
        
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }
    
    public TeamDTO toTeamDTO(Team team) {
        if (team == null) return null;
        
        TeamDTO dto = new TeamDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setSport(team.getSport());
        dto.setDescription(team.getDescription());
        dto.setCoach(toUserDTO(team.getCoach()));
        dto.setPlayers(team.getPlayers().stream()
                          .map(this::toUserDTO)
                          .collect(Collectors.toList()));
        dto.setPlayerCount(team.getPlayers().size());
        dto.setEventCount(team.getEvents().size());
        return dto;
    }
    
    public EventDTO toEventDTO(Event event) {
        if (event == null) return null;
        
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setStartTime(event.getStartTime());
        dto.setEndTime(event.getEndTime());
        dto.setLocation(event.getLocation());
        dto.setType(event.getType());
        dto.setTeam(toTeamDTO(event.getTeam()));
        dto.setMaxParticipants(event.getMaxParticipants());
        dto.setConfirmedParticipants((int) event.getParticipations().stream()
            .filter(p -> p.getStatus() == ParticipationStatus.CONFIRMED)
            .count());
        return dto;
    }
    
    public List<UserDTO> toUserDTOs(List<User> users) {
        return users.stream()
                   .map(this::toUserDTO)
                   .collect(Collectors.toList());
    }
    
    public List<TeamDTO> toTeamDTOs(List<Team> teams) {
        return teams.stream()
                   .map(this::toTeamDTO)
                   .collect(Collectors.toList());
    }
    
    public List<EventDTO> toEventDTOs(List<Event> events) {
        return events.stream()
                   .map(this::toEventDTO)
                   .collect(Collectors.toList());
    }
} 