package daniyar.hackaton.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import daniyar.hackaton.data.Event;
import daniyar.hackaton.data.User;
import daniyar.hackaton.exception.EventsNotFoundException;
import daniyar.hackaton.repository.EventRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Override
    public List<Event> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events;
    }

    @Override
    public List<Event> getEventsByName(String name) {
        List<Event> events = eventRepository.findByName(name)
                .orElseThrow(() -> new EventsNotFoundException("Events with such name not found"));

        return events;
    }

    @Override
    public Event addEvent(Event event) {
        Event createdEvent = eventRepository.save(event);
        return createdEvent;
    }

    @Override
    public Event getEventById(UUID id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventsNotFoundException("Event with such id not found"));
    }

    @Override
    public void addParticipantToEvent(User participant, UUID eventId) {
        eventRepository.addParticipantToEvent(participant, eventId);
    }

}
