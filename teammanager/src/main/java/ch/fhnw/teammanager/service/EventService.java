package ch.fhnw.teammanager.service;

import ch.fhnw.teammanager.model.Event;
import ch.fhnw.teammanager.repository.EventRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getEventsByTeamId(Long teamId) {
        return eventRepository.findByTeamId(teamId);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}