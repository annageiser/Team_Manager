package ch.fhnw.teammanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Team name is required")
    @Size(max = 100, message = "Team name must not exceed 100 characters")
    @Column(nullable = false)
    private String name;
    
    @NotBlank(message = "Sport is required")
    @Size(max = 50, message = "Sport must not exceed 50 characters")
    private String sport;
    
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
    
    @ManyToMany
    @JoinTable(
        name = "team_players",
        joinColumns = @JoinColumn(name = "team_id"),
        inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<User> players = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "coach_id")
    private User coach;
    
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Event> events = new ArrayList<>();
    
    // Constructors
    public Team() {}
    
    public Team(String name, String sport, String description) {
        this.name = name;
        this.sport = sport;
        this.description = description;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSport() { return sport; }
    public void setSport(String sport) { this.sport = sport; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public List<User> getPlayers() { return players; }
    public void setPlayers(List<User> players) { this.players = players; }
    
    public User getCoach() { return coach; }
    public void setCoach(User coach) { this.coach = coach; }
    
    public List<Event> getEvents() { return events; }
    public void setEvents(List<Event> events) { this.events = events; }
    
    // Helper methods
    public void addPlayer(User player) {
        if (!players.contains(player)) {
            players.add(player);
            player.getTeams().add(this);
        }
    }
    
    public void removePlayer(User player) {
        if (players.contains(player)) {
            players.remove(player);
            player.getTeams().remove(this);
        }
    }
    
    public int getPlayerCount() {
        return players.size();
    }
    
    public boolean hasPlayer(User player) {
        return players.contains(player);
    }
}