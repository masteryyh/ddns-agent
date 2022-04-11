package win.minaandyyh.ddnsagent.base.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Null-safe custom Jakarta Validation validator to check if a TimeUnit value is in a given subset
 *
 * @author masteryyh
 */
public class TimeUnitValidator implements ConstraintValidator<ValidTimeUnit, TimeUnit> {
    private TimeUnit[] subset;

    @Override
    public void initialize(ValidTimeUnit constraintAnnotation) {
        this.subset = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(TimeUnit value, ConstraintValidatorContext context) {
        return Objects.nonNull(value) && Arrays.asList(subset).contains(value);
    }
}
