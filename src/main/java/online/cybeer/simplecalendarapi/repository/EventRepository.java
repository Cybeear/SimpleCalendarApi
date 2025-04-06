package online.cybeer.simplecalendarapi.repository;

import java.util.UUID;
import online.cybeer.simplecalendarapi.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/07
 */
public interface EventRepository extends JpaRepository<Event, UUID> {
}
