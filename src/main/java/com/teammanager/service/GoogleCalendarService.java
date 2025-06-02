package com.teammanager.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
public class GoogleCalendarService {
    
    @Value("${google.calendar.application-name}")
    private String applicationName;
    
    @Value("${google.calendar.tokens-directory-path}")
    private String tokensDirectoryPath;
    
    private Calendar service;
    
    public GoogleCalendarService() throws GeneralSecurityException, IOException {
        // Initialize the calendar service
        service = new Calendar.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            JacksonFactory.getDefaultInstance(),
            null) // Will be set after OAuth2 flow
            .setApplicationName(applicationName)
            .build();
    }
    
    public String createEvent(com.teammanager.model.Event event) throws IOException {
        Event googleEvent = new Event()
            .setSummary(event.getTitle())
            .setLocation(event.getLocation())
            .setDescription(event.getType().toString());
            
        EventDateTime start = new EventDateTime()
            .setDateTime(new DateTime(event.getStartTime().toString()));
        googleEvent.setStart(start);
        
        EventDateTime end = new EventDateTime()
            .setDateTime(new DateTime(event.getEndTime().toString()));
        googleEvent.setEnd(end);
        
        // Insert the event
        Event createdEvent = service.events().insert("primary", googleEvent).execute();
        return createdEvent.getId();
    }
    
    public void updateEvent(String calendarId, com.teammanager.model.Event event) throws IOException {
        Event googleEvent = service.events().get("primary", calendarId).execute();
        
        googleEvent.setSummary(event.getTitle())
                  .setLocation(event.getLocation())
                  .setDescription(event.getType().toString());
                  
        EventDateTime start = new EventDateTime()
            .setDateTime(new DateTime(event.getStartTime().toString()));
        googleEvent.setStart(start);
        
        EventDateTime end = new EventDateTime()
            .setDateTime(new DateTime(event.getEndTime().toString()));
        googleEvent.setEnd(end);
        
        service.events().update("primary", calendarId, googleEvent).execute();
    }
    
    public void deleteEvent(String calendarId) throws IOException {
        service.events().delete("primary", calendarId).execute();
    }
} 