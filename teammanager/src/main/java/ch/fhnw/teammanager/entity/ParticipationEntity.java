package ch.fhnw.teammanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "participations")
public class Participation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    @NotNull
    private User player;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    @NotNull
    private Event event;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParticipationStatus status = ParticipationStatus.PENDING;
    
    @Column(nullable = false)
    private LocalDateTime responseTime = LocalDateTime.now();
    
    private String comment;
    
    // Constructors
    public Participation() {}
    
    public Participation(User player, Event event, ParticipationStatus status) {
        this.player = player;
        this.event = event;
        this.status = status;
        this.responseTime = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getPlayer() { return player; }
    public void setPlayer(User player) { this.player = player; }
    
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
    
    public ParticipationStatus getStatus() { return status; }
    public void setStatus(ParticipationStatus status) { 
        this.status = status;
        this.responseTime = LocalDateTime.now();
    }
    
    public LocalDateTime getResponseTime() { return responseTime; }
    public void setResponseTime(LocalDateTime responseTime) { this.responseTime = responseTime; }
    
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    
    // Helper methods
    public boolean isConfirmed() {
        return status == ParticipationStatus.CONFIRMED;
    }
    
    public boolean isDeclined() {
        return status == ParticipationStatus.DECLINED;
    }
    
    public boolean isPending() {
        return status == ParticipationStatus.PENDING;
    }
}