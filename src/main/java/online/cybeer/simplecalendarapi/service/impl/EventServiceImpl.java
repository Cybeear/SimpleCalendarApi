package online.cybeer.simplecalendarapi.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import online.cybeer.simplecalendarapi.converter.event.EventConverter;
import online.cybeer.simplecalendarapi.dto.event.EventRequest;
import online.cybeer.simplecalendarapi.dto.event.EventResponse;
import online.cybeer.simplecalendarapi.exception.UserAccessException;
import online.cybeer.simplecalendarapi.model.EventEntity;
import online.cybeer.simplecalendarapi.model.User;
import online.cybeer.simplecalendarapi.repository.EventRepository;
import online.cybeer.simplecalendarapi.repository.IdempotencyKeyRepository;
import online.cybeer.simplecalendarapi.service.EventService;
import online.cybeer.simplecalendarapi.utils.ContextUtils;
import online.cybeer.simplecalendarapi.utils.VerificationUtils;
import org.springframework.stereotype.Service;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/05
 */
@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final IdempotencyKeyRepository idempotencyKeyRepository;
    private final EventRepository repository;
    private final EventConverter converter;
    
    @Override
    public EventResponse createEvent(String idempotencyKey, EventRequest request) {
        return idempotencyKeyRepository.findByKey(idempotencyKey)
                .map(key -> getEventById(key.getEntityId()))
                .orElseGet(() -> createNewEvent(request));
    }

    @Override
    public EventResponse createNewEvent(EventRequest request) {
        User user = ContextUtils.getUser();
        EventEntity eventEntity = converter.toEntity(request, user.getId());
        return converter.toResponse(repository.save(eventEntity));
    }

    @Override
    public EventResponse updateEvent(UUID id, EventRequest request) {
        EventEntity event = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (ContextUtils.getUserId().equals(event.getUserId())) {
            throw new UserAccessException();
        }
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setStartTime(Long.valueOf(request.getStartTime()));
        event.setEndTime(Long.valueOf(request.getEndTime()));
        event.setLocation(request.getLocation());
        return converter.toResponse(repository.save(event));
    }
    
    @Override
    public EventResponse getEventById(UUID id) {
        return repository.findById(id).map(event -> {
            VerificationUtils.AccessVerification(event.getUserId());
            return converter.toResponse(event);
        }).orElse(null);
    }
    
    @Override
    public List<EventResponse> getAllEvents() {
        return repository.findAllByUserId(ContextUtils.getUserId()).stream().map(converter::toResponse).toList();
    }
    
    @Override
    public void deleteEvent(UUID id) {
        repository.deleteById(id);
    }
}
