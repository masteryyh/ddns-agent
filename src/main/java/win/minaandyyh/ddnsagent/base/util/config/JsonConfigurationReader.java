package win.minaandyyh.ddnsagent.base.util.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import win.minaandyyh.ddnsagent.base.model.ApplicationConfiguration;
import win.minaandyyh.ddnsagent.base.util.file.FileUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

/**
 * JSON-based configuration file reader
 *
 * @author masteryyh
 */
@Component
public class JsonConfigurationReader implements ConfigurationReader {
    private final Validator validator;

    private final ObjectMapper mapper;

    @Autowired
    public JsonConfigurationReader(Validator validator, ObjectMapper mapper) {
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public Optional<ApplicationConfiguration> read(CharSequence path) throws Exception {
        if (FileUtils.isValidPath(path)) {
            return Optional.empty();
        }

        ApplicationConfiguration configuration = mapper.readValue(Files.readString(Paths.get(path.toString())), ApplicationConfiguration.class);
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

        return Optional.of(configuration);
    }
}