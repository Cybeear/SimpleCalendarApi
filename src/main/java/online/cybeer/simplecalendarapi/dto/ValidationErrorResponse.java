package online.cybeer.simplecalendarapi.dto;

import java.util.List;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/07
 */
public record ValidationErrorResponse(String code, String message, Long timestamp, List<FieldError> errors) {
    public record FieldError(String field, String message) {
    }
}
