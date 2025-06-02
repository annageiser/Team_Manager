package com.teammanager.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "participations")
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "player_id")
    private User player;
    
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    
    @Enumerated(EnumType.STRING)
    private ParticipationStatus status;
    
    private LocalDateTime responseTime;
}

enum ParticipationStatus {
    CONFIRMED, DECLINED, PENDING
} 