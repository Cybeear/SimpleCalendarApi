package online.cybeer.simplecalendarapi.controller;

import online.cybeer.simplecalendarapi.dto.ErrorDto;
import online.cybeer.simplecalendarapi.exception.ErrorCode;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/05
 */
@RestControllerAdvice
public class ControllerAdvice {

    private final MessageSource messageSource;

    public ControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationException(
            MethodArgumentNotValidException ex,
            Locale locale // Auto-resolved from "Accept-Language" header
    ) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> messageSource.getMessage(fieldError, locale))
                .collect(Collectors.toList());

        String message = messageSource.getMessage(
                ErrorCode.VALIDATION_FAILED.getMessageKey(),
                new Object[]{String.join(", ", errors)},
                locale
        );

        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.value(), message);
        return ResponseEntity.badRequest().body(errorDto);
    }

    // Handle custom exceptions
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDto> handleUserNotFound(UsernameNotFoundException ex, Locale locale) {
        String message = messageSource.getMessage(
                ex.getMessage(),
                null,
                locale
        );
        ErrorDto errorDto = new ErrorDto(HttpStatus.NOT_FOUND.value(), message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    // Handle generic access denied
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDto> handleAccessDenied(Locale locale) {
        String message = messageSource.getMessage(
                ErrorCode.ACCESS_DENIED.getMessageKey(),
                null,
                locale
        );
        ErrorDto errorDto = new ErrorDto(HttpStatus.FORBIDDEN.value(), message);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDto);
    }
}