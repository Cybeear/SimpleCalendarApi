package online.cybeer.simplecalendarapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/05
 */

@Data
@Entity
@Table(name = "events")
@EqualsAndHashCode(callSuper = true)
public class EventEntity extends BaseUUIDEntity {
    
    private String title;
    
    private String description;
    
    private Long startTime;
    
    private Long endTime;
    
    private String location;
}
