package online.cybeer.simplecalendarapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.Instant;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/07
 */

@Data
@Entity
@Table(name = "events")
@EqualsAndHashCode(callSuper = true)
public class Event extends BaseUUIDEntity {

    private String title;

    private String description;

    @EqualsAndHashCode.Exclude
    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private Instant startTime;

    @EqualsAndHashCode.Exclude
    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private Instant endTime;

    private String location;
}
