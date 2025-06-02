package com.teammanager.service;

import com.teammanager.model.Participation;
import com.teammanager.model.Event;
import com.teammanager.model.User;
import com.teammanager.model.ParticipationStatus;
import com.teammanager.model.EventType;
import com.teammanager.repository.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ParticipationService {
    
    @Autowired
    private ParticipationRepository participationRepository;
    
    public Participation respondToEvent(Event event, User player, ParticipationStatus status) {
        Participation participation = participationRepository
            .findByEventAndPlayer(event, player)
            .orElse(new Participation());
            
        participation.setEvent(event);
        participation.setPlayer(player);
        participation.setStatus(status);
        participation.setResponseTime(LocalDateTime.now());
        
        // Check capacity for training events
        if (status == ParticipationStatus.CONFIRMED && 
            event.getType() == EventType.TRAINING) {
            int confirmedCount = participationRepository.countByEventAndStatus(
                event, ParticipationStatus.CONFIRMED);
            if (confirmedCount >= event.getMaxParticipants()) {
                throw new RuntimeException("Training session is full");
            }
        }
        
        return participationRepository.save(participation);
    }
    
    public List<Participation> getEventParticipations(Event event) {
        return participationRepository.findByEvent(event);
    }
    
    public List<Participation> getPlayerParticipations(User player) {
        return participationRepository.findByPlayer(player);
    }
    
    public List<Participation> getEventParticipationsByStatus(Event event, ParticipationStatus status) {
        return participationRepository.findByEventAndStatus(event, status);
    }
    
    public int getConfirmedParticipantsCount(Event event) {
        return participationRepository.countByEventAndStatus(event, ParticipationStatus.CONFIRMED);
    }
    
    public void deleteParticipation(Long id) {
        participationRepository.deleteById(id);
    }
} 