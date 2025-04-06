package online.cybeer.simplecalendarapi.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/05
 */
@Data
@AllArgsConstructor
public class EventResponse {
    private String id;
    private String title;
    private String description;
    private String startTimestamp;
    private String endTimestamp;
    private String location;
}
