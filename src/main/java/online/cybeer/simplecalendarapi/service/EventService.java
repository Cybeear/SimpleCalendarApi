package online.cybeer.simplecalendarapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import online.cybeer.simplecalendarapi.dto.event.EventRequest;
import online.cybeer.simplecalendarapi.dto.event.EventResponse;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/07
 */
public interface EventService {
    EventResponse createEvent(String idempotencyKey, EventRequest request);
    EventResponse createNewEvent(String idempotencyKey, EventRequest request);
    EventResponse updateEvent(UUID id, EventRequest request);
    EventResponse getEventById(UUID id);
    List<EventResponse> getAllEvents();
     void deleteEvent(UUID id);
}
