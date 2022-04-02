package win.minaandyyh.ddnsagent.base.util.validate;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.concurrent.ConcurrentInitializer;
import org.apache.commons.lang3.concurrent.LazyInitializer;

/**
 * Singleton validator
 *
 * @author masteryyh
 */
public class Validators {
    private Validators() {}

    private static final ConcurrentInitializer<Validator> INITIALIZER = new LazyInitializer<>() {
        @Override
        protected Validator initialize() {
            try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
                return factory.getValidator();
            }
        }
    };

    public static Validator getValidator() {
        Validator validator = null;
        try {
            validator = INITIALIZER.get();
        } catch (ConcurrentException e) {
            e.printStackTrace();
            // TODO: Utilize log4j
        }
        return validator;
    }
}
