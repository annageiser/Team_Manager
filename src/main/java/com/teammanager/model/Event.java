package com.teammanager.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    
    @Enumerated(EnumType.STRING)
    private EventType type;
    
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    
    private String googleCalendarId;
    
    @OneToMany(mappedBy = "event")
    private List<Participation> participations;
    
    private Integer maxParticipants;
}

enum EventType {
    TRAINING, GAME, MEETING
} 