package com.teammanager.dto;

import com.teammanager.model.EventType;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EventDTO {
    private Long id;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private EventType type;
    private TeamDTO team;
    private Integer maxParticipants;
    private Integer confirmedParticipants;
    private Boolean isUserParticipating;
} 