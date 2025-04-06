package online.cybeer.simplecalendarapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import online.cybeer.simplecalendarapi.dto.event.EventRequest;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/07
 */

public class EventDatesValidatorConstraint implements ConstraintValidator<EventDatesValidator, EventRequest> {

    @Override
    public void initialize(EventDatesValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(EventRequest eventRequest, ConstraintValidatorContext constraintValidatorContext) {
        if (eventRequest.getStartTimestamp() == null || eventRequest.getEndTimestamp() == null) return true;
        return eventRequest.getStartTimestamp().compareTo(eventRequest.getEndTimestamp()) < 0;
    }
}
