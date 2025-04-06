package online.cybeer.simplecalendarapi.exception;

import lombok.Getter;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/05
 */
@Getter
public enum ErrorCode {
    USER_NOT_FOUND("error.user.not_found"),
    EVENT_NOT_FOUND("error.event.not_found"),
    ACCESS_DENIED("error.access_denied"),
    INVALID_DATE("error.invalid_date"),
    VALIDATION_FAILED("error.validation_failed");

    private final String messageKey;

    ErrorCode(String messageKey) {
        this.messageKey = messageKey;
    }
}
