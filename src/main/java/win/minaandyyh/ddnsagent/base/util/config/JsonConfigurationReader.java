package win.minaandyyh.ddnsagent.base.util.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import win.minaandyyh.ddnsagent.base.model.ApplicationConfiguration;
import win.minaandyyh.ddnsagent.base.util.file.FileUtils;
import win.minaandyyh.ddnsagent.base.util.json.SingletonObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * JSON-based configuration file reader
 *
 * @author masteryyh
 */
public class JsonConfigurationReader implements ConfigurationReader {
    private final ObjectMapper mapper = SingletonObjectMapper.getInstance();

    @Override
    public Optional<ApplicationConfiguration> read(String path) throws Exception {
        if (FileUtils.isValidPath(path)) {
            return Optional.empty();
        }

        return Optional.of(mapper.readValue(Files.readString(Paths.get(path)), ApplicationConfiguration.class));
    }
}
