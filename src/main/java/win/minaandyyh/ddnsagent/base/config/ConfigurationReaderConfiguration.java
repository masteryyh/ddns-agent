package win.minaandyyh.ddnsagent.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import win.minaandyyh.ddnsagent.base.util.config.ConfConfigurationReader;
import win.minaandyyh.ddnsagent.base.util.config.ConfigurationReader;
import win.minaandyyh.ddnsagent.base.util.config.JsonConfigurationReader;

import java.util.Map;

/**
 * Configuration readers configuration class
 *
 * @author masteryyh
 */
@Configuration
@ComponentScan(basePackages = "win.minaandyyh.ddnsagent")
public class ConfigurationReaderConfiguration {
    private final JsonConfigurationReader jsonReader;

    private final ConfConfigurationReader confReader;

    @Autowired
    public ConfigurationReaderConfiguration(JsonConfigurationReader jsonReader, ConfConfigurationReader confReader) {
        this.jsonReader = jsonReader;
        this.confReader = confReader;
    }

    @Bean
    public Map<String, ConfigurationReader> getReaders() {
        return Map.of("json", jsonReader, "conf", confReader);
    }
}
