package online.cybeer.simplecalendarapi.converter.event;

import java.time.Instant;
import online.cybeer.simplecalendarapi.dto.event.EventRequest;
import online.cybeer.simplecalendarapi.dto.event.EventResponse;
import online.cybeer.simplecalendarapi.model.Event;
import org.springframework.stereotype.Component;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/07
 */
@Component
public class EventConverter {

    public Event toEntity(EventRequest request) {
        return toEntity(new Event(), request);
    }

    public Event toEntity(Event event, EventRequest request) {
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setStartTime(Instant.ofEpochMilli(request.getStartTimestamp()));
        event.setEndTime(Instant.ofEpochMilli(request.getEndTimestamp()));
        event.setLocation(request.getLocation());

        return event;
    }

    public EventResponse toResponse(Event event) {
        return new EventResponse(
                event.getId().toString(),
                event.getTitle(),
                event.getDescription(),
                event.getStartTime().toEpochMilli(),
                event.getEndTime().toEpochMilli(),
                event.getLocation()
        );
    }
}
