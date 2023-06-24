package daniyar.hackaton.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import daniyar.hackaton.data.Event;
import daniyar.hackaton.data.User;
import daniyar.hackaton.dto.EventDto;
import daniyar.hackaton.security.JwtService;
import daniyar.hackaton.service.EventService;
import daniyar.hackaton.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {
    private final ModelMapper mapper;
    private final EventService eventService;
    private final JwtService jwtService;
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
    public ResponseEntity<?> createEvent(@RequestBody EventDto eventDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        String userEmail = jwtService.extractEmail(token);

        User user = userService.getUserByEmail(userEmail);

        Event event = mapper.map(eventDto, Event.class);
        event.setUser(user);
        Event createdEvent = eventService.addEvent(event);

        return ResponseEntity.ok().body(createdEvent);
    }
}
