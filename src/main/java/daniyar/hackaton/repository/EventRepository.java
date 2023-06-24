package daniyar.hackaton.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import daniyar.hackaton.data.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    public Optional<List<Event>> findByName(String name);
}