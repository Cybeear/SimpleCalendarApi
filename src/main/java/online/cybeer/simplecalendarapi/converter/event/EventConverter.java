package online.cybeer.simplecalendarapi.converter.event;

import java.util.UUID;
import online.cybeer.simplecalendarapi.dto.event.EventRequest;
import online.cybeer.simplecalendarapi.dto.event.EventResponse;
import online.cybeer.simplecalendarapi.model.EventEntity;
import org.springframework.stereotype.Component;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/05
 */
@Component
public class EventConverter {

    public EventEntity toEntity(EventRequest request, UUID userId) {
        return toEntity(null, request);
    }
    
    public EventEntity toEntity(UUID id, EventRequest request) {
        EventEntity event = new EventEntity();
        event.setId(id);
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setStartTime(Long.valueOf(request.getStartTime()));
        event.setEndTime(Long.valueOf(request.getEndTime()));
        event.setLocation(request.getLocation());
        if (request.getUserId() != null) {
            event.setUserId(UUID.fromString(request.getUserId()));
        }
        return event;
    }

    public EventResponse toResponse(EventEntity event) {
        return new EventResponse(
                event.getId().toString(),
                event.getTitle(),
                event.getDescription(),
                event.getStartTime().toString(),
                event.getEndTime().toString(),
                event.getLocation()
        );
    }
}
