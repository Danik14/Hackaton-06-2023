package daniyar.hackaton.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import daniyar.hackaton.data.Event;
import daniyar.hackaton.data.User;
import daniyar.hackaton.exception.EventsNotFoundException;
import daniyar.hackaton.repository.EventRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Value("${stripe.api.key}")
    private String stripeApiKey;

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
    @Transactional
    public void addParticipantToEvent(User participant, UUID eventId) {
        Event event = getEventById(eventId);
        event.getEnrolledUsers().add(participant);
    }

    @Override
    public String createPaymentIntent(User participant, UUID eventId) throws StripeException {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventsNotFoundException("Event not found"));

        // Stripe requires the amount in cents
        Long feeAmount = event.getFee() * 100;

        Stripe.apiKey = stripeApiKey;

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(feeAmount)
                .setCurrency("usd")
                .setDescription("Event Enrollment Fee")
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        // Save the paymentIntent.getId() in your database for reference

        return paymentIntent.getClientSecret();

    }
}