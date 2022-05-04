package win.minaandyyh.ddnsagent.base.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import win.minaandyyh.ddnsagent.base.constant.Constants;
import win.minaandyyh.ddnsagent.base.model.ApplicationConfiguration;
import win.minaandyyh.ddnsagent.base.util.config.ConfigurationReader;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Read configuration files from environment variables or default locations to initialize configuration properties
 *
 * @author masteryyh
 */
@Configuration
public class ConfigurationInitializer {
    private final Map<String, ConfigurationReader> readers;

    public ConfigurationInitializer(Map<String, ConfigurationReader> readers) {
        this.readers = readers;
    }

    private String getFilePath() throws FileNotFoundException {
        String envPath = System.getProperty(Constants.ENV_VARIABLE);
        if (StringUtils.isNotEmpty(envPath)) {
            return envPath;
        }

        String currentLevel = "config.json";
        if (Files.exists(Paths.get(currentLevel))) {
            return currentLevel;
        }

        String defaultPath;
        if (SystemUtils.IS_OS_WINDOWS) {
            defaultPath = Constants.DEFAULT_CONFIG_LOCATION_WINDOWS;
        } else {
            defaultPath = Constants.DEFAULT_CONFIG_LOCATION_LINUX;
        }

        if (Files.exists(Paths.get(defaultPath))) {
            return defaultPath;
        }
        throw new FileNotFoundException("Cannot find configuration file.");
    }

    private ConfigurationReader getReader(String path) {
        if (StringUtils.isBlank(path)) {
            throw new IllegalArgumentException("File path is empty.");
        }

        String[] split = path.split("\\.");
        if (split.length != 2) {
            throw new IllegalArgumentException("No file extension name present or file path is invalid");
        }

        String type = split[1];
        if (!readers.containsKey(type)) {
            throw new IllegalArgumentException("Unknown file extension name " + type);
        }
        return readers.get(type);
    }

    @Bean
    @Conditional(InitializationConditionMatcher.class)
    public ApplicationConfiguration applicationConfiguration() throws Exception {
        String path = getFilePath();
        ConfigurationReader reader = getReader(path);
        return reader.read(path).orElseThrow(() ->
                new IllegalStateException("Configuration failed to parse."));
    }
}
