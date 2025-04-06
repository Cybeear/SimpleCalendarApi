package online.cybeer.simplecalendarapi.repository;

import java.util.Optional;
import online.cybeer.simplecalendarapi.model.IdempotencyKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/07
 */
public interface IdempotencyKeyRepository extends JpaRepository<IdempotencyKey, Long> {
    Optional<IdempotencyKey> findByIdempotencyToken(String key);
}
