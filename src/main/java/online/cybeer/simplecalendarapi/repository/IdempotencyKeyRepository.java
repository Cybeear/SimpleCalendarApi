package online.cybeer.simplecalendarapi.repository;

import java.util.Optional;
import java.util.UUID;
import online.cybeer.simplecalendarapi.dto.IdempotencyKey;
import online.cybeer.simplecalendarapi.model.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/05
 */
public interface IdempotencyKeyRepository extends JpaRepository<IdempotencyKey, Long> {
    Optional<IdempotencyKey> findByKey(String key);
}
