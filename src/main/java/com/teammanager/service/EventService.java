package com.teammanager.service;

import com.teammanager.model.Event;
import com.teammanager.model.Team;
import com.teammanager.model.EventType;
import com.teammanager.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class EventService {
    
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private GoogleCalendarService googleCalendarService;
    
    public Event createEvent(Event event) throws IOException {
        // Validate max participants for training
        if (event.getType() == EventType.TRAINING && 
            (event.getMaxParticipants() == null || event.getMaxParticipants() > 25)) {
            event.setMaxParticipants(25);
        }
        
        // Create event in database
        Event savedEvent = eventRepository.save(event);
        
        // Sync with Google Calendar
        String calendarEventId = googleCalendarService.createEvent(savedEvent);
        savedEvent.setGoogleCalendarId(calendarEventId);
        
        return eventRepository.save(savedEvent);
    }
    
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Event not found"));
    }
    
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    
    public List<Event> getTeamEvents(Team team) {
        return eventRepository.findByTeam(team);
    }
    
    public List<Event> getUpcomingEvents() {
        return eventRepository.findByStartTimeGreaterThanEqual(LocalDateTime.now());
    }
    
    public Event updateEvent(Long id, Event eventDetails) throws IOException {
        Event event = getEventById(id);
        
        event.setTitle(eventDetails.getTitle());
        event.setStartTime(eventDetails.getStartTime());
        event.setEndTime(eventDetails.getEndTime());
        event.setLocation(eventDetails.getLocation());
        event.setType(eventDetails.getType());
        event.setMaxParticipants(eventDetails.getMaxParticipants());
        
        // Update in Google Calendar
        if (event.getGoogleCalendarId() != null) {
            googleCalendarService.updateEvent(event.getGoogleCalendarId(), event);
        }
        
        return eventRepository.save(event);
    }
    
    public void deleteEvent(Long id) throws IOException {
        Event event = getEventById(id);
        
        // Delete from Google Calendar
        if (event.getGoogleCalendarId() != null) {
            googleCalendarService.deleteEvent(event.getGoogleCalendarId());
        }
        
        eventRepository.deleteById(id);
    }
    
    public boolean hasScheduleConflict(Event newEvent) {
        List<Event> teamEvents = eventRepository.findByTeamAndStartTimeBetween(
            newEvent.getTeam(),
            newEvent.getStartTime(),
            newEvent.getEndTime()
        );
        
        return !teamEvents.isEmpty();
    }
} 