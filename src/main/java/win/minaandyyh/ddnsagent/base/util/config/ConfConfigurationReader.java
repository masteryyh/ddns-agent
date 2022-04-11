package win.minaandyyh.ddnsagent.base.util.config;

import jakarta.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import win.minaandyyh.ddnsagent.base.constant.ConfigurationConstants;
import win.minaandyyh.ddnsagent.base.constant.Constants;
import win.minaandyyh.ddnsagent.base.model.*;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * .conf file reader
 *
 * @author masteryyh
 */
@Component("conf")
public class ConfConfigurationReader extends AbstractConfigurationReader implements ConfigurationReader {
    private final Map<DNSProvider, ProviderConfiguration> providerConfigurations = new EnumMap<>(DNSProvider.class);

    @Autowired
    public ConfConfigurationReader(Validator validator) {
        super(validator);
    }

    @PostConstruct
    public void initializeConfigurations() {
        providerConfigurations.put(DNSProvider.ALIYUN, new AliyunConfiguration());
        providerConfigurations.put(DNSProvider.CLOUDFLARE, new CloudflareConfiguration());
        providerConfigurations.put(DNSProvider.DNSPOD, new DNSPodConfiguration());
        providerConfigurations.put(DNSProvider.GODADDY, new GoDaddyConfiguration());
    }

    private boolean isLineInvalid(String line) {
        return StringUtils.isBlank(line) || line.split(Constants.EQUAL).length != 2;
    }

    private void readBasicConfig(String line, ApplicationConfiguration configuration) {
        if (isLineInvalid(line) || Objects.isNull(configuration)) {
            return;
        }

        String[] parts = line.split(Constants.EQUAL);
        String key = parts[0];
        String value = parts[1];
        if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
            return;
        }

        if (StringUtils.equals(key, ConfigurationConstants.DOMAIN) && StringUtils.isBlank(configuration.getDomain())) {
            configuration.setDomain(value);
        }

        if (StringUtils.equals(key, ConfigurationConstants.SUBDOMAIN) && StringUtils.isBlank(configuration.getSubDomain())) {
            configuration.setSubDomain(value);
        }

        if (StringUtils.equals(key, ConfigurationConstants.INTERVAL_UNIT) && Objects.isNull(configuration.getIntervalUnit())) {
            if (!StringUtils.equalsAny(value, ConfigurationConstants.TIME_UNITS.toArray(new String[0]))) {
                throw new IllegalArgumentException("Invalid time unit " + value);
            }
            configuration.setIntervalUnit(TimeUnit.valueOf(value));
        }

        if (StringUtils.equals(key, ConfigurationConstants.INTERVAL) && StringUtils.isNumeric(value)) {
            configuration.setInterval(Long.parseLong(value));
        }

        if (StringUtils.equals(key, ConfigurationConstants.PROVIDER) && Objects.isNull(configuration.getProvider())) {
            if (!StringUtils.equalsAny(value, ConfigurationConstants.VALID_PROVIDERS.toArray(new String[0]))) {
                return;
            }
            DNSProvider provider = DNSProvider.valueOf(value);
            configuration.setProvider(provider);
            configuration.setProviderSpecific(providerConfigurations.get(provider));
        }

        if (StringUtils.startsWith(key, ConfigurationConstants.PROVIDER_SPECIFIC) && Objects.nonNull(configuration.getProvider())) {
            if (Objects.isNull(configuration.getProviderSpecific())) {
                throw new IllegalStateException("DNS provider not defined.");
            }
            ProviderConfiguration providerSpecific = configuration.getProviderSpecific();
            providerSpecific.setValue(key, value);
            configuration.setProviderSpecific(providerSpecific);
        }
    }

    @Override
    public Optional<ApplicationConfiguration> read(CharSequence path) throws Exception {
        String config = Files.readString(Paths.get(path.toString()));
        if (StringUtils.isBlank(config)) {
            throw new IllegalArgumentException("Empty configuration.");
        }

        String[] lines = config.split("\n");
        ApplicationConfiguration configuration = new ApplicationConfiguration();
        Arrays.stream(lines).forEachOrdered(line ->
                readBasicConfig(line, configuration));

        validate(configuration);
        return Optional.of(configuration);
    }
}
