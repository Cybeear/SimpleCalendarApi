package online.cybeer.simplecalendarapi.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/07
 */
@Data
@AllArgsConstructor
public class EventResponse {
    private String id;
    private String title;
    private String description;
    private Long startTimestamp;
    private Long endTimestamp;
    private String location;
}
