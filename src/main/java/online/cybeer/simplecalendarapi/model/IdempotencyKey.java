package online.cybeer.simplecalendarapi.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import online.cybeer.simplecalendarapi.model.BaseUUIDEntity;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/05
 */
@Entity
@Data
@Table(name = "idempotency_key")
public class IdempotencyKey {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String key;

    @Column(nullable = false)
    private String entityType;

    @Column(nullable = false)
    private UUID entityId;
}
