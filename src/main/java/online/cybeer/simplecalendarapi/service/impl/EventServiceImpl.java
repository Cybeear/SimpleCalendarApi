package online.cybeer.simplecalendarapi.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import online.cybeer.simplecalendarapi.converter.event.EventConverter;
import online.cybeer.simplecalendarapi.dto.event.EventRequest;
import online.cybeer.simplecalendarapi.dto.event.EventResponse;
import online.cybeer.simplecalendarapi.model.Event;
import online.cybeer.simplecalendarapi.model.IdempotencyKey;
import online.cybeer.simplecalendarapi.repository.EventRepository;
import online.cybeer.simplecalendarapi.repository.IdempotencyKeyRepository;
import online.cybeer.simplecalendarapi.service.EventService;
import org.springframework.stereotype.Service;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/07
 */
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final IdempotencyKeyRepository idempotencyKeyRepository;
    private final EventRepository repository;
    private final EventConverter converter;

    @Override
    public EventResponse createEvent(String idempotencyToken, EventRequest request) {
        return idempotencyKeyRepository.findByIdempotencyToken(idempotencyToken)
                .map(key -> getEventById(key.getEntityId()))
                .orElseGet(() -> createNewEvent(idempotencyToken, request));
    }

    @Override
    public EventResponse createNewEvent(String idempotencyKey, EventRequest request) {
        IdempotencyKey key = new IdempotencyKey();
        key.setIdempotencyToken(idempotencyKey);
        Event event = repository.save(converter.toEntity(request));
        key.setEntityId(event.getId());
        idempotencyKeyRepository.save(key);
        return converter.toResponse(event);
    }

    @Override
    public EventResponse updateEvent(UUID id, EventRequest request) {
        Event event = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        converter.toEntity(event, request);
        return converter.toResponse(repository.save(event));
    }

    @Override
    public EventResponse getEventById(UUID id) {
        return repository.findById(id).map(converter::toResponse).orElse(null);
    }

    @Override
    public List<EventResponse> getAllEvents() {
        return repository.findAll().stream().map(converter::toResponse).toList();
    }

    @Override
    public void deleteEvent(UUID id) {
        repository.deleteById(id);
    }
}
