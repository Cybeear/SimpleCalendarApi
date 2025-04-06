package online.cybeer.simplecalendarapi.controller;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import online.cybeer.simplecalendarapi.dto.event.EventResponse;
import online.cybeer.simplecalendarapi.dto.event.EventRequest;
import online.cybeer.simplecalendarapi.service.impl.EventServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/05
 */
@AllArgsConstructor
@RestController
@RequestMapping("/events")
public class EventController {

    private final EventServiceImpl service;

    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@RequestHeader("Idempotency-Key") String idempotencyKey, 
                                                     @RequestBody EventRequest request) {
        return ResponseEntity.ok(service.createEvent(idempotencyKey, request));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> updateEvent(@PathVariable UUID id, @RequestBody EventRequest request) {
        return ResponseEntity.ok(service.updateEvent(id, request));
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> fetchAll() {
        return ResponseEntity.ok(service.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> fetchEventById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getEventById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id) {
        service.deleteEvent(id);
        return ResponseEntity.ok().build();
    }
}
