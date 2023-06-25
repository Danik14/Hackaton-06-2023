package daniyar.hackaton.controller;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import daniyar.hackaton.data.Event;
import daniyar.hackaton.data.User;
import daniyar.hackaton.dto.EventDto;
import daniyar.hackaton.service.EventService;
import daniyar.hackaton.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
@Slf4j
public class EventController {
    private final ModelMapper mapper;
    private final EventService eventService;
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<Event>> getAll() {
        return ResponseEntity.ok().body(eventService.getAllEvents());
    }

    @GetMapping("/name")
    public ResponseEntity<List<Event>> getOne(@PathVariable String name) {
        return ResponseEntity.ok().body(eventService.getEventsByName(name));
    }

    @PostMapping("")
    public ResponseEntity<?> createEvent(@RequestBody EventDto eventDto, Principal principal,
            UriComponentsBuilder uriBuilder) {
        String userEmail = principal.getName();
        User user = userService.getUserByEmail(userEmail);

        log.info(eventDto.toString());
        Event event = mapper.map(eventDto, Event.class);
        event.setUser(user);
        Event createdEvent = eventService.addEvent(event);

        // Build the URI for the created resource
        UriComponents uriComponents = uriBuilder.path("/event/{uuid}").buildAndExpand(createdEvent.getId());
        URI createdUri = uriComponents.toUri();
        if (createdEvent.isPrivate()) {
            return ResponseEntity.ok().body(List.of(createdEvent, createdUri));

        }
        return ResponseEntity.ok().body(createdEvent);
    }

    @PostMapping("/{uuid}/enroll")
    public ResponseEntity<?> participateInEvent(@PathVariable String uuid, Principal principal) {
        UUID id = UUID.fromString(uuid);
        if (uuid == null) {
            throw new IllegalArgumentException("Invalid UUID format");
        }

        String userEmail = principal.getName();
        User user = userService.getUserByEmail(userEmail);

        eventService.addParticipantToEvent(user, id);

        return ResponseEntity.ok().body("Successfuly added a participant");
    }
}
