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
@Constraint(validatedBy = XSSValidatorConstraint.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface XSSValidator {
    String message() default "Potential XSS attack detected";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
