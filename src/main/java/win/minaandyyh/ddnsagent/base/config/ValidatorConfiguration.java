package win.minaandyyh.ddnsagent.base.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Jakarta Validator configuration
 *
 * @author masteryyh
 */
@Configuration
public class ValidatorConfiguration {
    @Bean
    public Validator getValidator() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            return factory.getValidator();
        }
    }
}
