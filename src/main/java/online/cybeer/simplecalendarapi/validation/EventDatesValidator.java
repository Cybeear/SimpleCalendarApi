package online.cybeer.simplecalendarapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/07
 */
@Documented
@Constraint(validatedBy = EventDatesValidatorConstraint.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventDatesValidator {
    String message() default "Start date after or equal to end date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
