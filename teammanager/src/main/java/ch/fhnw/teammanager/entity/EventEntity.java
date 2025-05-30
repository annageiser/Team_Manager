package ch.fhnw.teammanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Event title is required")
    @Size(max = 200, message = "Title must not exceed 200 characters")
    @Column(nullable = false)
    private String title;
    
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;
    
    @NotNull(message = "Start time is required")
    @Column(nullable = false)
    private LocalDateTime startTime;
    
    @NotNull(message = "End time is required")
    @Column(nullable = false)
    private LocalDateTime endTime;
    
    @Size(max = 200, message = "Location must not exceed 200 characters")
    private String location;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventType type = EventType.TRAINING;
    
    private Integer maxParticipants = 25; // Default limit for trainings
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
    
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Participation> participations = new ArrayList<>();
    
    // Google Calendar Integration
    private String googleCalendarId;
    private String googleEventId;
    
    // Constructors
    public Event() {}
    
    public Event(String title, LocalDateTime startTime, LocalDateTime endTime, EventType type, Team team) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.team = team;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public EventType getType() { return type; }
    public void setType(EventType type) { this.type = type; }
    
    public Integer getMaxParticipants() { return maxParticipants; }
    public void setMaxParticipants(Integer maxParticipants) { this.maxParticipants = maxParticipants; }
    
    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }
    
    public List<Participation> getParticipations() { return participations; }
    public void setParticipations(List<Participation> participations) { this.participations = participations; }
    
    public String getGoogleCalendarId() { return googleCalendarId; }
    public void setGoogleCalendarId(String googleCalendarId) { this.googleCalendarId = googleCalendarId; }
    
    public String getGoogleEventId() { return googleEventId; }
    public void setGoogleEventId(String googleEventId) { this.googleEventId = googleEventId; }
    
    // Helper methods
    public int getCurrentParticipantCount() {
        return (int) participations.stream()
                .filter(p -> p.getStatus() == ParticipationStatus.CONFIRMED)
                .count();
    }
    
    public boolean hasSpaceAvailable() {
        return maxParticipants == null || getCurrentParticipantCount() < maxParticipants;
    }
    
    public boolean isUserParticipating(User user) {
        return participations.stream()
                .anyMatch(p -> p.getPlayer().equals(user) && p.getStatus() == ParticipationStatus.CONFIRMED);
    }
    
    public Participation getUserParticipation(User user) {
        return participations.stream()
                .filter(p -> p.getPlayer().equals(user))
                .findFirst()
                .orElse(null);
    }
    
    public boolean overlaps(Event other) {
        return this.startTime.isBefore(other.endTime) && this.endTime.isAfter(other.startTime);
    }
}