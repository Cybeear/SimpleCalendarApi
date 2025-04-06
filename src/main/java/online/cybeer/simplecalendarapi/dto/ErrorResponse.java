package online.cybeer.simplecalendarapi.dto;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/07
 */
public record ErrorResponse(String code, String message, Long timestamp, Map<String, String> details) {
    public ErrorResponse(String code, String message) {
        this(code, message, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC), null);
    }
}
