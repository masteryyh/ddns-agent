package win.minaandyyh.ddnsagent.base.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * Custom Jakarta Validation annotation to check if a TimeUnit value is in a given subset
 *
 * @author masteryyh
 */
@Target({
        ElementType.METHOD,
        ElementType.FIELD,
        ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.PARAMETER,
        ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = TimeUnitValidator.class)
public @interface ValidTimeUnit {
    TimeUnit[] anyOf();

    String message() default "must be any of {anyOf}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
