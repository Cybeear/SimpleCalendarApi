package online.cybeer.simplecalendarapi.repository;

import java.util.List;
import java.util.UUID;
import online.cybeer.simplecalendarapi.model.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/05
 */
public interface EventRepository extends JpaRepository<EventEntity, UUID> {
    List<EventEntity> findAllByUserId(UUID userId);
}
