package daniyar.hackaton.service;

import java.util.List;

import daniyar.hackaton.data.Event;

public interface EventService {
    List<Event> getAllEvents();

    List<Event> getEventsByName(String name);

    Event addEvent(Event event);
}
