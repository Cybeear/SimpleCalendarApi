package online.cybeer.simplecalendarapi.controller;

import jakarta.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import online.cybeer.simplecalendarapi.dto.ErrorResponse;
import online.cybeer.simplecalendarapi.dto.ValidationErrorResponse;
import online.cybeer.simplecalendarapi.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/07
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleValidationExceptions(MethodArgumentNotValidException exception) {
        List<ValidationErrorResponse.FieldError> fieldErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ValidationErrorResponse.FieldError(
                        error.getField(),
                        error.getDefaultMessage()))
                .toList();

        return new ValidationErrorResponse(
                "VALIDATION_FAILED",
                "Validation failed for the request",
                LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
                fieldErrors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleConstraintViolation(ConstraintViolationException exception) {
        List<ValidationErrorResponse.FieldError> fieldErrors = exception.getConstraintViolations()
                .stream()
                .map(violation -> new ValidationErrorResponse.FieldError(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()))
                .toList();

        return new ValidationErrorResponse(
                "CONSTRAINT_VIOLATION",
                "Database constraint violation",
                LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
                fieldErrors);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException exception) {
        HttpStatus status = determineHttpStatus(exception);
        ErrorResponse response = new ErrorResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
                null);

        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUncaughtExceptions(Exception exception) {
        log.error("Unhandled exception occurred", exception);
        return new ErrorResponse(
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred",
                LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
                Map.of("detail", "Please contact support"));
    }

    private HttpStatus determineHttpStatus(BaseException exception) {
        return switch (exception.getErrorCode()) {
            case "RESOURCE_NOT_FOUND" -> HttpStatus.NOT_FOUND;
            case "BUSINESS_RULE_VIOLATION" -> HttpStatus.BAD_REQUEST;
            case "CONFLICT" -> HttpStatus.CONFLICT;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {
        return new ErrorResponse(
                "MALFORMED_REQUEST",
                "Request body is malformed or missing",
                LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
                Map.of("detail", exception.getMostSpecificCause().getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception) {
        return new ErrorResponse(
                "INVALID_ARGUMENT_TYPE",
                String.format("Parameter '%s' should be of type %s",
                        exception.getName(),
                        exception.getRequiredType().getSimpleName()),
                LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
                null);
    }
}
