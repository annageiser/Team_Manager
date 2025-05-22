package ch.fhnw.teammanager.controller;

import ch.fhnw.teammanager.model.Event;
import ch.fhnw.teammanager.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @GetMapping("/team/{teamId}")
    public List<Event> getEventsByTeam(@PathVariable Long teamId) {
        return eventService.getEventsByTeamId(teamId);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}