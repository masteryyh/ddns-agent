package win.minaandyyh.ddnsagent.base.util.config;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.apache.commons.collections4.CollectionUtils;
import win.minaandyyh.ddnsagent.base.model.ApplicationConfiguration;

import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * Abstract basic class of configuration reader
 *
 * @author masteryyh
 */
public abstract class AbstractConfigurationReader implements ConfigurationReader {
    private final Validator validator;

    protected AbstractConfigurationReader(Validator validator) {
        this.validator = validator;
    }

    /**
     * Validate a configuration object using Jakarta Validation validator
     *
     * @param configuration {@link ApplicationConfiguration} object
     */
    protected void validate(ApplicationConfiguration configuration) {
        if (Objects.isNull(configuration)) {
            throw new IllegalArgumentException("Null configuration object");
        }

        Set<ConstraintViolation<ApplicationConfiguration>> violations = validator.validate(configuration);
        if (CollectionUtils.isNotEmpty(violations)) {
            Iterator<ConstraintViolation<ApplicationConfiguration>> iterator = violations.iterator();
            StringBuilder messageBuilder = new StringBuilder();

            while (iterator.hasNext()) {
                ConstraintViolation<ApplicationConfiguration> violation = iterator.next();
                messageBuilder.append(violation.getMessage());

                if (iterator.hasNext()) {
                    messageBuilder.append(", ");
                }
            }
            messageBuilder.insert(0, "Configuration is invalid, message as follows: ");
            throw new IllegalArgumentException(messageBuilder.toString());
        }
    }
}
