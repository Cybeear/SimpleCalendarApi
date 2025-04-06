package online.cybeer.simplecalendarapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Types;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/07
 */
@Entity
@Data
@Table(name = "idempotency_key")
public class IdempotencyKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String idempotencyToken;

    @Column(columnDefinition = "CHAR(36)", nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID entityId;
}
