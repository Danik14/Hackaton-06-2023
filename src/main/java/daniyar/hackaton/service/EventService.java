package daniyar.hackaton.service;

import java.util.List;
import java.util.UUID;

import daniyar.hackaton.data.Event;
import daniyar.hackaton.data.User;

public interface EventService {
    List<Event> getAllEvents();

    List<Event> getEventsByName(String name);

    void addParticipantToEvent(User participant, UUID eventId);

    Event getEventById(UUID id);

    Event addEvent(Event event);
}
