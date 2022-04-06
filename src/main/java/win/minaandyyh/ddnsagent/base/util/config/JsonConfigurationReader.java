package win.minaandyyh.ddnsagent.base.util.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import win.minaandyyh.ddnsagent.base.model.ApplicationConfiguration;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * JSON-based configuration file reader
 *
 * @author masteryyh
 */
@Component
public class JsonConfigurationReader extends AbstractConfigurationReader implements ConfigurationReader {
    private final ObjectMapper mapper;

    @Autowired
    public JsonConfigurationReader(Validator validator, ObjectMapper mapper) {
        super(validator);
        this.mapper = mapper;
    }

    @Override
    public Optional<ApplicationConfiguration> read(CharSequence path) throws Exception {
        ApplicationConfiguration configuration = mapper.readValue(Files.readString(Paths.get(path.toString())), ApplicationConfiguration.class);
        validate(configuration);

        return Optional.of(configuration);
    }
}
