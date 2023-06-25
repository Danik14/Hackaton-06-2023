package daniyar.hackaton.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import daniyar.hackaton.data.Event;
import daniyar.hackaton.data.User;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    public Optional<List<Event>> findByName(String name);

    @Modifying
    @Query("UPDATE Event e SET e.enrolledUsers = :participant WHERE e.id = :eventId")
    void addParticipantToEvent(@Param("participant") User participant, @Param("eventId") UUID eventId);
}